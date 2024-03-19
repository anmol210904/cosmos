package com.example.cosmos.navigation

import com.example.cosmos.R

sealed  class NavItem(var title:String, var icon:Int?, var screen_route:String) {
    object Main : NavItem("Main", null, "main")
    object Home : NavItem("Home", R.drawable.home, "home")
    object Apod : NavItem("Apod",R.drawable.apod,"apod")
    object Profile : NavItem("Profile", R.drawable.profile,"profile")
    object Auth_Login : NavItem("Login", null, "login" )
    object Auth_SignUp : NavItem("SignUp", null , "sign_up")
    object Add_Post : NavItem("AddPost", R.drawable.add_post, "add_post")
}