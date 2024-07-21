package com.sydney.sydney.ui.theme.live


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.sydney.sydney.R
import com.sydney.sydney.ui.theme.live.Hostel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HostelViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    val hostelState = MutableLiveData<Hostel>()

    fun getHostel(hostelId: String) {
        viewModelScope.launch {
            val document = firestore.collection("live").document(hostelId).get().await()
            val hostel = document.toObject(Hostel::class.java) ?: return@launch
            hostelState.value = hostel
        }
    }

    fun updateHostel(hostel: Hostel) {
        firestore.collection("live").document(hostel.id).set(hostel)
    }
}

@Composable
fun UpdateHostelScreen(navController: NavController, hostelId: String, hostelViewModel: HostelViewModel) {

    var updatedHostelName by remember { mutableStateOf("") }
    var updatedHostelDescription by remember { mutableStateOf("") }
    var updatedHostelPrice by remember { mutableStateOf("") }
    var updatedHostelImageUri by remember { mutableStateOf<Uri?>(null) }

    val hostelState by hostelViewModel.hostelState.observeAsState()

    val hostel = hostelState ?: Hostel()

    updatedHostelName = hostel.name
    updatedHostelDescription = hostel.description
    updatedHostelPrice = hostel.price.toString()
    updatedHostelImageUri = Uri.parse(hostel.imageUrl)

    val storage = FirebaseStorage.getInstance()
    fun uploadImageToStorage(hostelId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
        if (imageUri != null) {
            val storageRef = storage.reference.child("hostel_images").child("$hostelId.jpg")
            val uploadTask = storageRef.putFile(imageUri)
            uploadTask.addOnSuccessListener { taskSnapshot ->
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }
        }
    }

    val context = LocalContext.current
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        updatedHostelImageUri = uri
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Edit Hostel", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        updatedHostelImageUri?.let { uri ->
            Image(
                painter = painterResource(id = R.drawable.colfind), // Placeholder image
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = updatedHostelName,
            onValueChange = { updatedHostelName = it },
            label = { Text(updatedHostelName.takeUnless { it.isBlank() } ?: "Hostel Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = updatedHostelDescription,
            onValueChange = { updatedHostelDescription = it },
            label = { Text(updatedHostelDescription.takeUnless { it.isBlank() } ?: "Hostel Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = updatedHostelPrice,
            onValueChange = { updatedHostelPrice = it },
            label = { Text(updatedHostelPrice.takeUnless { it.isBlank() } ?: "Hostel Price") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            getContent.launch("image/*")
        }) {
            Text("Select Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val updatedHostel = Hostel(
                id = hostelId,
                name = updatedHostelName,
                description = updatedHostelDescription,
                price = updatedHostelPrice.toDouble(),
                imageUrl = ""
            )

            if (updatedHostelImageUri != null) {
                uploadImageToStorage(hostelId, updatedHostelImageUri) { imageUrl ->
                    updatedHostel.imageUrl = imageUrl
                    hostelViewModel.updateHostel(updatedHostel)
                    navController.popBackStack()
                }
            } else {
                hostelViewModel.updateHostel(updatedHostel)
                navController.popBackStack()
            }
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Save")
        }
    }
}
