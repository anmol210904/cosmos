package com.example.cosmos

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.navigation.BottomNavGraph
import com.example.cosmos.navigation.BottomNavigation
import com.example.cosmos.navigation.NavGraph
import com.example.cosmos.ui.theme.CosmosTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    val navHostController = rememberNavController()

                    NavGraph(navHostController = navHostController)
                    


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CosmosTheme {
        Greeting("Android")
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainScreenView(navHostController: NavHostController) {
    
    val bottomNavHostController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(navHostController = bottomNavHostController) }
    ) {
        BottomNavGraph(navHostController = bottomNavHostController, mainNavController = navHostController)
    }
    
}