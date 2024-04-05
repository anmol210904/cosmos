package com.example.cosmos.presentation.marsPhotos

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.presentation.marsPhotos.compoents.MarsPop
import com.example.cosmos.viewModel.MarsRoverViewModel
import org.koin.androidx.compose.koinViewModel


@Preview(showBackground = true)
@Composable
fun MarsRoverPhotosScreen(
    navHostController: NavHostController = rememberNavController(),
    date: String = "2014-02-01"
) {

    val viewModel: MarsRoverViewModel = koinViewModel()

    val data by viewModel.data.observeAsState()



    LaunchedEffect(key1 = Unit) {
        viewModel.getDate(date)
    }

    val photos = arrayOf(
        "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FRB_486615455EDR_F0481570FHAZ00323M_.JPG",
        "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FRB_486615455EDR_F0481570FHAZ00323M_.JPG",
        "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FRB_486615455EDR_F0481570FHAZ00323M_.JPG",
        "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FRB_486615455EDR_F0481570FHAZ00323M_.JPG"
    )
    //UI
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
    ) {

        data?.photos?.let {
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {

                items(it) {photo->

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

                    if(showPopUp.value){
                        MarsPop(photo = photo, popUpVisibility = showPopUp)
                    }

                    Log.d("MARS_ROVER_PHOTOS_SCREEN", photo.img_src)

                    Spacer(modifier = Modifier.size(16.dp))
                }
            }

        }
//        AsyncImage(model = "https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00530/opgs/edr/fcam/FRB_444538224EDR_F0260184FHAZ00323M_.JPG", contentDescription = null)
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
