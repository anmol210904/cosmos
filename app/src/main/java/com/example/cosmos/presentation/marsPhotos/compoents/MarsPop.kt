package com.example.cosmos.presentation.marsPhotos.compoents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.cosmos.models.marsRover.Photo


@Composable
fun MarsPop(
    photo: Photo,
    popUpVisibility : MutableState<Boolean>
) {


    Dialog(onDismissRequest = { popUpVisibility.value = false }) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .wrapContentHeight()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {


                //id
                Text(text = "Id", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Divider()
                Text(text = photo.id.toString(), fontSize = 12.sp)

                Spacer(modifier = Modifier.size(8.dp))

                Text(text = "sol", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Divider()
                Text(text = photo.sol.toString(), fontSize = 12.sp)

                Spacer(modifier = Modifier.size(8.dp))


                //camera
                Text(text = "camera", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Divider()
                Spacer(modifier = Modifier.size(4.dp))

                Row() {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "id", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Divider()
                        Text(text = photo.camera.id.toString(), fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Divider()
                        Text(text = photo.camera.name, fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.size(4.dp))
                Row(

                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "rover id", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Divider()
                        Text(text = photo.camera.rover_id.toString() ,fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Full Name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Divider()
                        Text(text = photo.camera.full_name, fontSize = 12.sp)
                    }
                }



                Spacer(modifier = Modifier.size(8.dp))

                Text(text = "earth date", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Divider()
                Text(text = photo.earth_date, fontSize = 12.sp)

                Spacer(modifier = Modifier.size(12.dp))

                Row {
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.weight(1f)) {
                        Text(text = "Rover", fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.weight(1f)) {
                        Text(text = "Rover", fontSize = 12.sp)
                    }



                }


            }

        }
    }


}

