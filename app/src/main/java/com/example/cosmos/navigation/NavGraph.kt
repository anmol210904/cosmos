package com.example.cosmos.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cosmos.presentation.AppPostScreen.AddPostScreen
import com.example.cosmos.presentation.Auth.SignInScreen
import com.example.cosmos.presentation.Auth.SignUpScreen
import com.example.cosmos.presentation.apod.ApodScreen
import com.example.cosmos.presentation.home.HomeScreen
import com.example.cosmos.presentation.profile.ProfileScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController,
        startDestination = if(Firebase.auth.currentUser.toString() != "")NavItem.Home.screen_route else NavItem.Auth_Login.screen_route){
        composable(NavItem.Home.screen_route){
            HomeScreen()
        }
        composable(NavItem.Apod.screen_route){
            ApodScreen(navHostController)
        }
        composable(NavItem.Profile.screen_route){
            ProfileScreen()
        }
        composable(NavItem.Auth_Login.screen_route){
            SignInScreen(navHostController)
        }

        composable(NavItem.Auth_SignUp.screen_route){
            SignUpScreen(navHostController)
        }

        composable(NavItem.Add_Post.screen_route){
            AddPostScreen(navHostController)
        }
    }
}