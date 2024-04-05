package com.example.cosmos.presentation.Modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.navigation.NavItem

@Preview(showBackground = true)
@Composable
fun ModulesScreen(
    navHostController: NavHostController = rememberNavController()
) {
    LaunchedEffect(key1 = null) {
//        navHostController.navigate(NavItem.Earth.screen_route){
//            popUpTo(0)
//        }
    }


    Column {
        Column(modifier = Modifier.weight(1f)) {
            Button(onClick = {
                navHostController.navigate(NavItem.Earth.screen_route)
            }) {
                Text(text = "Earth")
            }

            Spacer(modifier =Modifier.size(32.dp))

            Button(onClick = {
                navHostController.navigate(NavItem.MarsRoverDate.screen_route)
            }) {
                Text(text = "Earth")
            }


        }




    }
}