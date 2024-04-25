package com.example.cosmos.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cosmos.MainScreenView
import com.example.cosmos.presentation.AppPostScreen.AddPostScreen
import com.example.cosmos.presentation.Auth.OnboardingScreen
import com.example.cosmos.presentation.Auth.SignInScreen
import com.example.cosmos.presentation.Auth.SignUpScreen
import com.example.cosmos.presentation.apod.ApodScreen
import com.example.cosmos.presentation.earth.EarthScreen
import com.example.cosmos.presentation.home.HomeScreen
import com.example.cosmos.presentation.marsPhotos.MarsRoverDateScreen
import com.example.cosmos.presentation.marsPhotos.MarsRoverPhotosScreen
import com.example.cosmos.presentation.posts.EditScreen
import com.example.cosmos.presentation.posts.PostsScreen
import com.example.cosmos.presentation.profile.MyProfileScreen
import com.example.cosmos.presentation.profile.OtherProfileScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController,
        startDestination = if(Firebase.auth.currentUser != null)NavItem.Main.screen_route else NavItem.Onboarding.screen_route){
        composable(NavItem.Home.screen_route){
            HomeScreen(navHostController)
        }
        composable(NavItem.Apod.screen_route){
            ApodScreen(navHostController)
        }
        composable(NavItem.Profile.screen_route){
            MyProfileScreen(navHostController)
        }
        composable(NavItem.Auth_Login.screen_route){
            SignInScreen(navHostController)
        }


        composable(NavItem.Onboarding.screen_route){
            OnboardingScreen(navHostController)
        }

        composable(NavItem.Auth_SignUp.screen_route){
            SignUpScreen(navHostController)
        }

        composable(NavItem.Add_Post.screen_route){
            AddPostScreen(navHostController)
        }
        composable(NavItem.Main.screen_route){
            MainScreenView(navHostController)
        }

        composable(route = NavItem.Post.screen_route + "/{postId}",
            arguments = listOf(
                navArgument(name = "postId"){ type = NavType.StringType}

        )  ){
             PostsScreen(navHostController = navHostController, postId = it.arguments?.getString("postId"))
        }


        composable(route = NavItem.Earth.screen_route){
            EarthScreen(navHostController)
        }

        composable(route = NavItem.MarsRoverDate.screen_route){
            MarsRoverDateScreen(navHostController)
        }

        composable(route = NavItem.MarsRoverPhotos.screen_route + "/{date}", arguments = listOf(
            navArgument("date"){type = NavType.StringType}
        )){
            it.arguments?.getString("date")
                ?.let { it1 -> MarsRoverPhotosScreen(navHostController, date = it1) }
        }

        composable(route = NavItem.OtherProfile.screen_route + "/{userId}",
            arguments = listOf(
                navArgument("userId"){NavType.StringType}
            )
        ){
            it.arguments?.let { it1 ->
                it1.getString("userId")?.let { it2 ->
                    OtherProfileScreen(
                        mainNavController = navHostController,
                        userId = it2
                    )
                }
            }
        }

        composable(route = NavItem.Edit.screen_route){
            EditScreen(navHostController)
        }
    }
}