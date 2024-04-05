package com.example.cosmos.presentation.home

import com.example.cosmos.R
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.presentation.utils.PostView
import com.example.cosmos.viewModel.GetPostViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.ScopeID

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(mainNavController: NavHostController) {

    val viewModel = koinViewModel<GetPostViewModel>()

    val data by viewModel.posts.observeAsState()
  
    LaunchedEffect (key1 = Unit){
        viewModel.getPosts()
    }

    val liststate  = rememberLazyListState()

//    mainNavController.navigate(NavItem.Post.screen_route)


    if(data != null){
        Column (modifier = Modifier
            .fillMaxSize(1f)
//            .background(color = colorResource(id = R.color.background))
            .padding(16.dp)){
            LazyColumn(state = liststate) {
                if(data != null){
                    items(data!!){
                        PostView(it, navController = mainNavController, onClickAction = {
                            mainNavController.navigate(NavItem.Post.screen_route + "/${it.postId}"){
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        })
                        Spacer(modifier = Modifier.size(8.dp))
                        Divider()
                    }
                }

            }
        }
    }

}