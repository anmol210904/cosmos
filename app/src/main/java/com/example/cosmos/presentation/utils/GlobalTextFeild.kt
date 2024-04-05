package com.example.cosmos.presentation.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun GlobalTextField(
    text: MutableState<String> = mutableStateOf(""),
    label: String = "label",
    leadingIcon: ImageVector? = null,
    leadingIconInt: Int? = null,
    modifier: Modifier = Modifier.fillMaxWidth(1f),
    keyboardOptions : KeyboardOptions = KeyboardOptions(),
    onFocus: (FocusState) -> Unit = {}
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
                } else if(leadingIconInt != null){
                    Icon(
                        painter = painterResource(id = leadingIconInt),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.Unspecified
                    )
                }
            },
            modifier = modifier.onFocusChanged {
                onFocus(it)
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),

            keyboardOptions = keyboardOptions
        )




}