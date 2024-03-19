package com.example.cosmos.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun ProfileScreen (){
    LaunchedEffect(key1 = Unit) {
        Firebase.auth.signOut()

    }
}