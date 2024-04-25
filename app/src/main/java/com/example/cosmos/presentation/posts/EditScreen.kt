package com.example.cosmos.presentation.posts

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.api.resource.Response
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.viewModel.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun EditScreen(
    navController: NavController = rememberNavController()
) {

    var fullName = remember { mutableStateOf("") }
    var username = remember { mutableStateOf("") }

    val viewModel = koinViewModel<ProfileViewModel>()
    val user by viewModel.profile.observeAsState()
    val editResponse by viewModel.editUserResponse.observeAsState()


    LaunchedEffect(Unit) {
        Firebase.auth.uid?.let { viewModel.getProfile(it) }
    }


    // image
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    var isloading by remember {
        mutableStateOf(false)
    }


    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImage = uri })


    editResponse?.let {
        when(it){
            is Response.Error -> {
                isloading = false
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()


            }
            is Response.Loading -> {
                isloading = true
            }
            is Response.Success -> {
                isloading = false
                Toast.makeText(LocalContext.current, "Success", Toast.LENGTH_SHORT).show()
                navController.navigate(NavItem.Main.screen_route){
                    popUpTo(0)
                }
            }
            else -> {

            }

        }
    }

    Scaffold(
        topBar = {
            TopBar {
                navController.popBackStack()
            }
        }
    ) {
        it.calculateTopPadding()
        if (user != null) {
            LaunchedEffect(Unit) {
                fullName.value = user!!.name
                username.value = user!!.username
            }
            Column(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                AsyncImage(
                    model = if (selectedImage == null) user!!.img else selectedImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(
                            CircleShape
                        )
                        .background(Color.Gray)
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.weight(1f))


                OutlinedTextField(
                    value = fullName.value,
                    onValueChange = { change ->
                        fullName.value = change
                    },
                    modifier = Modifier.fillMaxWidth(1f),
                    textStyle = TextStyle(fontFamily = FontFamily.Serif),

                    shape = RoundedCornerShape(8.dp),
                    label = { Text(text = "Full Name", fontFamily = FontFamily.Serif) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                )

                Spacer(modifier = Modifier.size(8.dp))

                OutlinedTextField(
                    value = username.value,
                    onValueChange = { change ->
                        username.value = change
                    },
                    textStyle = TextStyle(fontFamily = FontFamily.Serif),
                    modifier = Modifier.fillMaxWidth(1f),
                    shape = RoundedCornerShape(8.dp),
                    label = { Text(text = "Username", fontFamily = FontFamily.Serif) },
                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
                )
                Spacer(modifier = Modifier.weight(0.4f))

                Button(
                    onClick = {

                        viewModel.editProfile(username.value, fullName.value ,selectedImage)
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(8.dp),
                    enabled = fullName.value != "" && username.value != "" && !isloading
                ) {
                    if (isloading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(text = "Login")
                    }
                }
                Spacer(modifier = Modifier.weight(0.2f))


            }
        }
    }
}


@Composable
fun TopBar(
    onBlackClick: () -> Unit
) {
    Row(
        modifier = Modifier
//            .height(32.dp)
            .fillMaxWidth(1f)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "Edit Profile",
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    }
}