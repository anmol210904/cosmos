package com.example.cosmos.presentation.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavInflater
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.R
import com.example.cosmos.navigation.NavItem


@Preview(showBackground = true)
@Composable
fun OnboardingScreen(
    navHostController: NavHostController = rememberNavController()
) {


    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.White)
            .padding(16.dp)

    ) {

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.astraunaut),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(1f)
        )

        Spacer(modifier = Modifier.weight(1f))



        Text(
            text = "Discover the cosmos. \nConnect with fellow space enthusiasts. \nWelcome to your cosmic journey!",
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))




        Button(onClick = {
                         navHostController.navigate(NavItem.Auth_SignUp.screen_route)
        },
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(52.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Sign up", modifier = Modifier)
        }
        
        Spacer(modifier = Modifier.size(8.dp))

        Button(onClick = {
                         navHostController.navigate(NavItem.Auth_Login.screen_route)
        },
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(52.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_gray))
        ) {
            Text(text = "Login", color = MaterialTheme.colorScheme.primary)
        }




    }


}