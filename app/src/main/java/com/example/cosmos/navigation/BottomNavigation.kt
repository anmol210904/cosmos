package com.example.cosmos.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cosmos.navigation.NavItem.Apod.screen_route


@Composable
fun BottomNavigation(navHostController: NavHostController) {
    val items = listOf(
        NavItem.Home,
        NavItem.Apod,
        NavItem.Add_Post,
        NavItem.MODULE,
        NavItem.Profile

    )

    var currentRoute by remember { mutableStateOf(0) }

    NavigationBar(modifier = Modifier.height(48.dp)) {
        items.forEachIndexed { index, Item ->
            NavigationBarItem(
                selected = index == currentRoute,
                onClick = { currentRoute = index
                          navHostController.navigate(Item.screen_route){
                              popUpTo(screen_route) {
                                  saveState = true
                              }
                          }},
                icon = {
                    Item.icon?.let { painterResource(id = it) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                })

        }
    }

}