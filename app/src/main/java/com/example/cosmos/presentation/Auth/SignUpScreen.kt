package com.example.cosmos.presentation.Auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.presentation.utils.GlobalTextField
import com.example.cosmos.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable

fun SignUpScreen(navHostController: NavHostController = rememberNavController()) {

    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val loading = remember { mutableStateOf(false) }


    //

    val viewModel = koinViewModel<AuthViewModel>()

    val data by viewModel.signUp.observeAsState()

    data.let {
        when (it) {
            "Success" -> {
                navHostController.navigate(NavItem.Main.screen_route) {
                    popUpTo(0)
                }
            }

            "Loading" -> {
                loading.value = true
            }

            "Error" -> {
                loading.value = false
            }

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlobalTextField(name, label = "Name", leadingIcon = Icons.Rounded.Create)
        GlobalTextField(email, label = "Email", leadingIcon = Icons.Rounded.Email)
        GlobalTextField(pass, label = "Password", leadingIcon = Icons.Rounded.Lock)
        GlobalTextField(userName, label = "Username", leadingIcon = Icons.Rounded.Person)
        Spacer(modifier = Modifier.size(32.dp))


        //signUp button
        Button(onClick = {
            viewModel.signUp(email.value, userName.value, pass.value, name.value)
        }, modifier = Modifier.width(200.dp)) {
            if (!loading.value) {
                Text(text = "SignUp", modifier = Modifier.padding(horizontal = 16.dp))
            }

        }


    }
}