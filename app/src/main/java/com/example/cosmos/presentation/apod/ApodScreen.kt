package com.example.cosmos.presentation.apod

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.presentation.utils.GlobalTextField
import com.example.cosmos.presentation.utils.Post1
import com.example.cosmos.viewModel.ApodViewModel
import org.koin.androidx.compose.koinViewModel


@Preview(showBackground = true)
@Composable
fun ApodScreen(navHostController: NavHostController = rememberNavController()) {

    val viewModel = koinViewModel<ApodViewModel>()

    val data by viewModel.data.observeAsState()
    viewModel.test()
    LaunchedEffect (key1 = Unit){
        viewModel.getData()
    }


    if(data != null){
        Log.d("TAG123", data!![0].explanation)
    }

    //values

    val seachText = remember { mutableStateOf("") }


    //UI stats here
    Column(
        modifier = Modifier
            .fillMaxSize(1f)

//            .background(color = colorResource(id = R.color.background))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlobalTextField(seachText, "Search", leadingIcon = Icons.Rounded.Search)
        Spacer(modifier = Modifier.size(8.dp))

        if(data != null){
            LazyColumn {
                items(data!!){
                    Post1(it)
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()

                }
            }
        }
    }


}
