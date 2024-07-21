package com.sydney.sydney.ui.theme.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun ProfileScreen(userViewModel: UserViewModel = viewModel(),navController: NavController) {
    val name by userViewModel.name.observeAsState("")
    val email by userViewModel.email.observeAsState("")
    val location by userViewModel.location.observeAsState("")
    val imageUrl by userViewModel.imageUrl.observeAsState("")
    val campus by userViewModel.campus.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Name: $name", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Email: $email", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Location: $location", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Campus: $campus", modifier = Modifier.padding(bottom = 8.dp))
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(128.dp)
                .padding(top = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}