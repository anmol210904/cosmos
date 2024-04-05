package com.example.cosmos.presentation.Auth

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.presentation.utils.GlobalTextField
import com.example.cosmos.viewModel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun SignInScreen(navHostController: NavHostController = rememberNavController()){

    //values

    val email = remember{ mutableStateOf("") }
    val pass =  remember{ mutableStateOf("") }
    val loading =  remember{ mutableStateOf(false) }


    //

    val viewModel = koinViewModel<AuthViewModel>()

    val data by viewModel.login.observeAsState()

    data.let {
        when(it){
             "Success" ->{
                navHostController.navigate(NavItem.Main.screen_route){
                    popUpTo(0)
                }
            }
            "Loading" ->{
                loading.value = true
            }
            "Error" -> {
                loading.value = false
            }

        }
    }

    //UI

    Column(modifier = Modifier
        .fillMaxSize(1f)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {



        //Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,


            ) {

            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(48.dp)) {
                Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.weight(1f))

            Text(text = "Cosmos", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.weight(1f))


            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(48.dp)) {
//                Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(32.dp))
            }
        }

        Divider()




        Spacer(modifier = Modifier.weight(1f))

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            GlobalTextField(text = email, label = "Email", leadingIcon = Icons.Rounded.Email)
            GlobalTextField(text = pass, label = "Password", leadingIcon = Icons.Rounded.Lock)


        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Dont Have an account : Login?", color = Color.Blue,
            modifier = Modifier.clickable {
                navHostController.navigate(NavItem.Auth_SignUp.screen_route)
            })

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { viewModel.login(email.value, pass.value) }, modifier = Modifier.width(200.dp)) {
            if(loading.value){
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp))
            }
            else{
                Text(text = "Login")
            }
        }
    }



}