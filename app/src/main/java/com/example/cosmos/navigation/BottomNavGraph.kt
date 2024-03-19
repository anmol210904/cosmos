package com.example.cosmos.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cosmos.presentation.AppPostScreen.AddPostScreen
import com.example.cosmos.presentation.apod.ApodScreen
import com.example.cosmos.presentation.home.HomeScreen
import com.example.cosmos.presentation.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavGraph(navHostController: NavHostController, mainNavController: NavHostController) {
    NavHost(navController = navHostController,
        startDestination = NavItem.Home.screen_route)
    {
        composable(NavItem.Home.screen_route){
            HomeScreen(mainNavController)
        }
        composable(NavItem.Apod.screen_route){
            ApodScreen(mainNavController)
        }
        composable(NavItem.Profile.screen_route){
            ProfileScreen(mainNavController)
        }


        composable(NavItem.Add_Post.screen_route){
            AddPostScreen(mainNavController)
        }
    }
}