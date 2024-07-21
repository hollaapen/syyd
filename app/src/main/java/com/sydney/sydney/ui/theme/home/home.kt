package com.sydney.sydney.ui.theme.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sydney.sydney.R
import com.sydney.sydney.navigation.ROUTE_ADD_HOSTELS
import com.sydney.sydney.navigation.ROUTE_LOGIN
import com.sydney.sydney.navigation.ROUTE_PROFILE
import com.sydney.sydney.navigation.ROUTE_REGISTER
import com.sydney.sydney.navigation.ROUTE_SEARCH


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var isDrawerOpen by remember { mutableStateOf(false) }

    val callLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { _ ->

        }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Sydney")
                },
//                navigationIcon = @Composable {
//                    if (!isDrawerOpen) {
//                        IconButton(onClick = { isDrawerOpen = true }) {
//                            Icon(
//                                Icons.Default.Menu,
//                                contentDescription = "Menu",
//                                tint = Color.White
//                                )
//                        }
//                    }
//                },

                actions = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_PROFILE)

                    }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White,

                    )

            )
        },

        content = @Composable {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        if (isDrawerOpen) {
                            isDrawerOpen = false
                        }
                    }
            ) {


                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(painter = painterResource(id = R.drawable.colfind) , contentDescription = "", contentScale = ContentScale.Crop
                                ,modifier= Modifier
                                    .size(200.dp)
                                    .clip(RoundedCornerShape(10.dp)))
                            LazyRow (modifier = Modifier

                            ){
                                item {
                                    Image(painter = painterResource(id = R.drawable.room), contentDescription = "")
                                    Image(painter = painterResource(id = R.drawable.kitch), contentDescription = "")
                                    Image(painter = painterResource(id = R.drawable.space), contentDescription = "")

                                }

                            }




                            Text(
                                text = "sydney",
                                fontSize = 20.sp,
                                color=Color.White
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Row {

                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.White)
                                        .clickable {
                                            navController.navigate(ROUTE_LOGIN)
                                        },
                                    text = "Login ",
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                    color =Color.Black,
                                    fontWeight = FontWeight.SemiBold

                                )
                                Spacer(modifier = Modifier.width(100.dp))
                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.White)
                                        .clickable {
                                            navController.navigate(ROUTE_REGISTER)
                                        },
                                    text = "Register",
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                    color =Color.Black,
                                    fontWeight = FontWeight.SemiBold
                                )




                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Row (modifier = Modifier,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically){
                                Text(text = "Having a problem?",
                                    fontSize = 15.sp,
                                    color= Color.White,
                                    fontFamily = FontFamily.Cursive
                                )
                                Text(
                                    text =  "Call me",
                                    fontWeight =FontWeight.SemiBold ,
                                    fontSize = 20.sp,
                                    color= Color.White,
                                    modifier = Modifier
                                        .clickable {

                                            val intent = Intent(Intent.ACTION_DIAL)
                                            intent.data = Uri.parse("tel:0794817115")

                                            callLauncher.launch(intent)
                                        }

                                )

                            }

                            Text(
                                modifier = Modifier

                                    .clickable {
                                        navController.navigate(ROUTE_ADD_HOSTELS)
                                    },
                                text = "Add Hostels",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                color = Color.White
                            )


                            Spacer(modifier = Modifier.height(108.dp))




                        }}

                }

            }

        },


        bottomBar = { BottomBar(navController = navController) }

    )

//    AnimatedDrawer(
//        isOpen = isDrawerOpen,
//        onClose = { isDrawerOpen = false }
//    )
}

//@Composable
//fun AnimatedDrawer(isOpen: Boolean, onClose: () -> Unit) {
//    val drawerWidth = remember { Animatable(if (isOpen) 150f else 0f) }
//
//    LaunchedEffect(isOpen) {
//        drawerWidth.animateTo(if (isOpen) 150f else 0f, animationSpec = tween(durationMillis = 200))
//    }
//
//    Surface(
//        modifier = Modifier
//            .fillMaxHeight()
//            .width(drawerWidth.value.dp),
//            color = Color.Black,
//    //      elevation = 16.dp
//    ) {
//        Column {
//            Text(
//                text = "Drawer Item 1"
//
//            )
//            Text(
//                text = "Drawer Item 2"
//            )
//            Text(
//                text = "Drawer Item 3",
//                modifier = Modifier.clickable {  }
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(text = stringResource(id = R.string.developer))
//
//        }
//    }
//}






@Composable
fun BottomBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = Color.Black

    ) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"", tint = Color.White)
        },
            label = { Text(text = "Home",color =  Color.White) }, selected = (selectedIndex.value == 0), onClick = {

            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"",tint = Color.White)
        },
            label = { Text(text = "Favorite",color =  Color.White) }, selected = (selectedIndex.value == 1), onClick = {

            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, "",tint = Color.White)
        },
            label = { Text(
                text = "Students",
                color =  Color.White) },
            selected = (selectedIndex.value == 2),
            onClick = {

                navController.navigate(ROUTE_SEARCH)

            })

    }
}