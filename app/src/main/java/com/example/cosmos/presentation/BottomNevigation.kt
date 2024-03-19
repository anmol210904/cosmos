package com.example.cosmos.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomePageBottomBar(
    selectedItem: MutableState<Int>,
    items: List<String>,
    icons: List<ImageVector>
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(64.dp)
    ) {
        Row(Modifier.padding(start = 15.dp, end = 15.dp)) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem.value == index
                NavigationBarItem(
                    icon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = null
                            )
//                            Spacer(modifier = Modifier.width(1.dp)) // Add spacing between icon and text
                            if (isSelected) {
                                Text(text = item, fontSize = 10.sp)
                            }
                        }
                    },

                    selected = isSelected,
                    onClick = { selectedItem.value = index

                    },
                    alwaysShowLabel = isSelected
                )
            }

        }
    }
}