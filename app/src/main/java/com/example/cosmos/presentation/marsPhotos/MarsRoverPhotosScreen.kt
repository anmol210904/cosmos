package com.example.cosmos.presentation.marsPhotos

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.api.resource.Response
import com.example.cosmos.presentation.marsPhotos.compoents.MarsPop
import com.example.cosmos.viewModel.MarsRoverViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import org.koin.androidx.compose.koinViewModel


@Preview(showBackground = true)
@Composable
fun MarsRoverPhotosScreen(
    navHostController: NavHostController = rememberNavController(),
    date: String = "2014-02-01"
) {

    val viewModel: MarsRoverViewModel = koinViewModel()

    val data by viewModel.data.observeAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getDate(date)
    }

    data?.let {
        when (it) {
            is Response.Error -> {
                isLoading = false
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
                navHostController.popBackStack()

            }

            is Response.Loading -> {
                isLoading = true

            }

            is Response.Success -> {
                isLoading = false
                if (it.data!!.photos.size == 0) {
                    Toast.makeText(
                        LocalContext.current,
                        "The is no data for the date input",
                        Toast.LENGTH_SHORT
                    ).show()
                    navHostController.popBackStack()
                } else {

                }
            }
        }
    }


    //UI
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
    ) {

        if (!isLoading) {
            data?.data?.photos?.let {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(it) { photo ->

                        val showPopUp = remember {
                            mutableStateOf(false)
                        }
                        AsyncImage(
                            model = convertToHttps(photo.img_src),
                            contentDescription = null,
                            modifier = Modifier

//                            .background(Color.Gray)
                                .padding(1.dp)
                                .clickable {
                                    showPopUp.value = true
                                }
                        )

                        if (showPopUp.value) {
                            MarsPop(photo = photo, popUpVisibility = showPopUp)
                        }

                        Log.d("MARS_ROVER_PHOTOS_SCREEN", photo.img_src)

                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }

            }
        } else {

            Column(
                modifier = Modifier.fillMaxSize(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(32.dp))
            }


        }
    }
}

fun convertToHttps(url: String): String {
    // Check if the URL starts with "http://"
    return if (url.startsWith("http://")) {
        // Replace "http://" with "https://"
        "https://" + url.substring(7)
    } else {
        // If it doesn't start with "http://", prepend "https://" to the original URL
        "https://$url"
    }
}
