package com.example.cosmos.presentation.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.cosmos.R

@Composable
fun ReportPopUp(
    onclick: () -> Unit,
    open : MutableState<Boolean>
) {

    if (open.value) {
        Popup(
            onDismissRequest = {
                open.value = false
            }
        ) {
            Column (
                modifier = Modifier.fillMaxSize(1f).padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ){
                Row(
                    modifier = Modifier
                        .clickable {
                            onclick()
                        }
                        .clip(
                            RoundedCornerShape(4.dp)
                        )
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.exclamation),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "Report")
                }
            }
        }
    }







}