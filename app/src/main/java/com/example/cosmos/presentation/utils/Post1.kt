package com.example.cosmos.presentation.utils

import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.models.apod.ApodResponseClass
import org.jetbrains.annotations.Async
import java.time.format.TextStyle

@Preview(showBackground = true)
@Composable
fun Post1(data: ApodResponseClass = ApodResponseClass(), onClick: () -> Unit = {}) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(16.dp))
//            .background(color = colorResource(id = R.color.postColor))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //title
        Text(
            text = data.title,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth(1f),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))

        //media


        AsyncImage(model = data.url, contentDescription = null)
        Spacer(modifier = Modifier.size(8.dp))


        //date
        Text(
            text = "Date: ${data.date}",
            modifier = Modifier.fillMaxWidth(1f),
            fontSize = 12.sp,
            // Adjust the line spacing value as needed
        )

        Spacer(modifier = Modifier.size(8.dp))

        //desp
        Text(
            text = data.explanation,
            modifier = Modifier.height(35.dp),
            fontSize = 14.sp,
            style = androidx.compose.ui.text.TextStyle(lineHeight = 1.2.em)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "Read More",
            modifier = Modifier
                .clickable { }
                .fillMaxWidth(1f)
                .padding(end = 16.dp),
            textAlign = TextAlign.End,
            color = Color.Blue,
            textDecoration = TextDecoration.Underline)


    }
}