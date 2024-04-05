package com.example.cosmos.presentation.marsPhotos

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
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
        modifier = Modifier.fillMaxSize(1f)
    ) {

        data?.let {

            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)){
                items(it.photos) {
                    AsyncImage(model = it.img_src, contentDescription = null)
                    Log.d("MARS_ROVER_PHOTOS",it.img_src)
                }
            }
        }


    }
}