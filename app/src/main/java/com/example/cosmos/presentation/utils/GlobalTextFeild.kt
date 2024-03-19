package com.example.cosmos.presentation.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun GlobalTextField(
    text: MutableState<String> = mutableStateOf(""),
    label: String = "label",
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier.fillMaxWidth(1f)
) {
    OutlinedTextField(
        value = text.value, onValueChange = {
            text.value = it
        },
        label = { Text(text = label) },
        leadingIcon = {
            if(leadingIcon != null){
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        modifier = modifier
        )



}