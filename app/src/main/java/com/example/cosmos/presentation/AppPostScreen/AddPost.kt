package com.example.cosmos.presentation.AppPostScreen

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.api.resource.Response
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.viewModel.AddPostViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddPostScreen(
    mainNavHostController: NavHostController = rememberNavController()
) {

    val viewModel = koinViewModel<AddPostViewModel>()

    val addPostResult by viewModel.addPostResult.observeAsState()

    var isLoading by remember{ mutableStateOf(false) }


    var desp by remember {
        mutableStateOf("")
    }
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }


    addPostResult?.let {
        when(it){
            is Response.Error -> {
                isLoading= false
                Toast.makeText(LocalContext.current, it.errorMessage.toString(),Toast.LENGTH_LONG).show()

            }
            is Response.Loading -> {
                isLoading = true
            }
            is Response.Success -> {
                isLoading = false
                selectedImage = null
                desp = ""
                Toast.makeText(LocalContext.current, "Uploaded Successfully",Toast.LENGTH_LONG).show()
                mainNavHostController.navigate(NavItem.Main.screen_route){
                    popUpTo(0)
                }

            }
        }
    }





    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImage = uri })

    //UI
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
//            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        //Top bar
        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(1f)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {
                    selectedImage?.let { viewModel.addImage(it, desp = desp) }
                }, modifier = Modifier.height(36.dp), enabled = selectedImage != null
            ) {
                if(!isLoading){
                    Text(text = "Post")
                }else{
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp))

                }
            }
        }

        Box(
            modifier = Modifier
                .height(0.5.dp)
                .fillMaxWidth(1f)
                .background(Color.Gray)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 16.dp)
        ) {


            AsyncImage(
                model = "https://apod.nasa.gov/apod/image/2401/PlutoTrueColor_NewHorizons_960.jpg",
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(32.dp)
                    .clip(CircleShape)

            )

            Spacer(modifier = Modifier.size(8.dp))


            Box(
                modifier = Modifier

                    .padding(top = 12.dp)
                    .height(300.dp)
                    .width(0.5.dp)
                    .background(color = Color.LightGray)
            )

//            Spacer(modifier = Modifier.size(2.dp))

            Column(
                modifier = Modifier.wrapContentHeight()
//                    .background(Color.Gray)
//                    .wrapContentHeight()
            ) {

                OutlinedTextField(
                    value = desp,
                    onValueChange = {
                        desp = it
                    },
                    placeholder = {
                        Text(
                            text = "Add Post.", color = Color.Gray, fontSize = 14.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .wrapContentHeight(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 14.sp)

                )


                if (selectedImage == null) {
                    IconButton(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }, modifier = Modifier.size(48.dp)
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.image_add),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )

                    }
                } else {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(4.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // AsyncImage inside a Box
                        AsyncImage(
                            model = selectedImage,
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        // Close button at top right

                        IconButton(onClick = {
                            selectedImage = null
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }

                    }
                }


            }

        }


    }
}