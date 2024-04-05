package com.example.cosmos.presentation.earth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.presentation.utils.GlobalTextField
import com.example.cosmos.viewModel.EarthViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun EarthScreen(
    navHostController: NavHostController = rememberNavController(),

) {

    val viewModel: EarthViewModel = koinViewModel()

    val data by viewModel.earthData.observeAsState()

    val lat = remember {
        mutableStateOf("")
    };
    val long = remember {
        mutableStateOf("")
    }

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
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //latitide input


        GlobalTextField(
            text = long,
            label = "Longitude",
            leadingIconInt = R.drawable.longitude
        )

        Spacer(modifier = Modifier.size(24.dp))

        GlobalTextField(
            text = lat,
            label = "Latitude",
            leadingIconInt = R.drawable.latitude
        )


        //longitude input 



        Spacer(modifier = Modifier.size(24.dp))


        // date input
        GlobalTextField(
            text = date, modifier = Modifier.width(180.dp), label = "Date of Birth",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,

                ),
            leadingIconInt = R.drawable.date
        ) { focusState: FocusState ->
            if (focusState.hasFocus) {
                calendarState.show()
            } else {
                calendarState.hide()
            }
        }

        Spacer(modifier = Modifier.size(24.dp))


        // fetch button

        Button(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 64.dp),
            onClick = {
                viewModel.getData(longitude = long.value, latitude = lat.value,date=date.value)
            }
        ) {


            Text(text = "Fetch")

        }

        Spacer(modifier = Modifier.size(32.dp))
        // Image View

        AsyncImage(
            model = data?.url ?: "", contentDescription = null,
            modifier = if(false) Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight() else Modifier
                .fillMaxWidth(1f)
                .height(300.dp)
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .background(color = Color.LightGray),
            contentScale = ContentScale.FillWidth
        )


    }
}

fun convertDateFormat(inputDate: String): String {
    val parts = inputDate.split("-")
    if (parts.size != 3) {
        throw IllegalArgumentException("Invalid date format. Expected yyyy-mm-dd.")
    }
    val year = parts[0]
    val month = parts[1]
    val day = parts[2]
    return "$year-$month-$day"
}
