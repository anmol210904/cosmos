package com.example.cosmos.presentation.Modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.R


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
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,


        ) {

            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(48.dp)) {
                Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.weight(1f))

            Text(text = "Modules", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.weight(1f))


            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(48.dp)) {
//                Icon(imageVector = Icons.Rounded.KeyboardArrowLeft, contentDescription = null, modifier = Modifier.size(32.dp))
            }
        }

        Divider()





        Column(modifier = Modifier.weight(1f).padding(16.dp)) {
            Button(onClick = {
                navHostController.navigate(NavItem.Earth.screen_route)
            }) {
                Text(text = "Earth Engine")
            }

            Spacer(modifier = Modifier.size(32.dp))

            Button(onClick = {
                navHostController.navigate(NavItem.MarsRoverDate.screen_route)
            }) {
                Text(text = "Mars Rover Photos")
            }


        }


    }
}