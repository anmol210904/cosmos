package com.example.cosmos.presentation.Auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.api.resource.Response
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

    data?.let {
        when(it){
            is Response.Error -> {
                loading.value = false
                Toast.makeText(LocalContext.current,it.errorMessage,Toast.LENGTH_LONG).show()
            }
            is Response.Loading -> {
                loading.value = true
            }
            is Response.Success -> {
                loading.value = false
                navHostController.navigate(NavItem.Main.screen_route)
            }
            else -> {

            }

        }

    }




    Scaffold(
        topBar = {
            AuthTopBar {
                navHostController.popBackStack()
            }
        }
    ) {
        it.calculateTopPadding()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), bottom = 32.dp)
                .padding(horizontal = 16.dp)
        ){



            Spacer(modifier = Modifier.size(32.dp))

            //some text


            Text(
                text = "Sign Up and start Exploring!",
                modifier = Modifier.fillMaxWidth(1f),
                fontFamily = FontFamily.Serif,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(lineHeight = 1.2.em)
            )


            Spacer(modifier = Modifier.weight(1f))


            //name
            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                modifier = Modifier.fillMaxWidth(1f),
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = "Full name") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
            )

            Spacer(modifier = Modifier.size(8.dp))



            //username
            OutlinedTextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                modifier = Modifier.fillMaxWidth(1f),
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = "Username") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
            )

            Spacer(modifier = Modifier.size(8.dp))

            

            //Email

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                modifier = Modifier.fillMaxWidth(1f),
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = "Type your email") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
            )

            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            Spacer(modifier = Modifier.size(8.dp))

            //Password
            OutlinedTextField(
                value = pass.value,
                onValueChange = {
                    pass.value = it
                },
                modifier = Modifier.fillMaxWidth(1f),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = "Type your password") },
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.LightGray)
            )

            Spacer(modifier = Modifier.size(64.dp))



            Button(
                onClick = { viewModel.signUp(
                    name = name.value,
                    email = email.value,
                    pass = pass.value,
                    userName = userName.value
                ) },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(54.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (loading.value) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp))
                } else {
                    Text(text = "Sign up")
                }
            }

            Spacer(modifier = Modifier.weight(0.4f))



            Row {
                Text(text = "Don't have an account? ", fontFamily = FontFamily.Serif)
                Text(
                    text = "Login",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.clickable {
                        navHostController.navigate(NavItem.Auth_Login.screen_route)
                    }
                )
            }
        }
    }
}