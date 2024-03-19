package com.example.cosmos.presentation.AppPostScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.presentation.utils.GlobalTextField
import com.example.cosmos.viewModel.AddPostViewModel
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun AddPostScreen(
    navHostController: NavHostController = rememberNavController()
) {

    val viewModel = koinViewModel<AddPostViewModel>()


    var desp by remember {
        mutableStateOf("")
    }
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }


    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImage = uri })

    //UI
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Box(modifier = Modifier
            .wrapContentSize()
            .clickable {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
            if (selectedImage == null) {
                Image(
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            } else {
                AsyncImage(
                    model = selectedImage, contentDescription = null,
                    modifier = Modifier
                        .width(300.dp)
                        .clip(RoundedCornerShape(32.dp))
                )
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = desp, onValueChange = {
                desp = it
            },
            label = { Text(text = "Description or Queries?")},
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(200.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { selectedImage?.let { viewModel.addImage(it,desp) } }, modifier = Modifier
            .width(300.dp)
            .height(40.dp)) {
            Text(text = "Post")
        }


    }
}