package com.example.cosmos.presentation.marsPhotos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cosmos.R
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.presentation.earth.convertDateFormat
import com.example.cosmos.presentation.utils.GlobalTextField
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Preview(showBackground = true)
@Composable
fun MarsRoverDateScreen(
    navHostController: NavHostController = rememberNavController()
) {


    val date = remember {
        mutableStateOf("")
    }

    val calendarState = rememberUseCaseState()



    DateTimeDialog(
        state = calendarState,
        selection = DateTimeSelection.Date { newDate ->
            date.value = convertDateFormat(newDate.toString())
        }
    )



    //UI
    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // date input
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            GlobalTextField(
                text = date, modifier = Modifier.width(180.dp), label = "Select Date",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,

                    ),
                leadingIconInt = R.drawable.date,

                ) { focusState: FocusState ->
                if (focusState.hasFocus) {
                    calendarState.show()
                } else {
                    calendarState.hide()
                }
            }
        }

        // button
        Button(onClick = {
            navHostController.navigate(NavItem.MarsRoverPhotos.screen_route + "/${date.value}")
        },
            modifier = Modifier.padding(32.dp),
            enabled = date.value != null) {
            Text(text = "Fetch", modifier = Modifier.padding(horizontal = 32.dp))
        }

    }
}