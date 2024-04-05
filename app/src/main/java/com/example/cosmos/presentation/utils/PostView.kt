package com.example.cosmos.presentation.utils

import android.os.Build
import android.service.autofill.OnClickAction
import androidx.annotation.RequiresApi
import androidx.compose.foundation.CombinedClickableNode
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.models.post.PostModel
import com.example.cosmos.navigation.NavItem


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PostView(
    post: GetPostModel = GetPostModel(),
    navController: NavController = rememberNavController(),
    onClickAction: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(16.dp))
//            .background(color = colorResource(id = R.color.postColor))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //title
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {


            AsyncImage(
                model = post.userImg, contentDescription = null, modifier = Modifier
                    .size(32.dp)
                    .clip(
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = post.username)

        }
        Spacer(modifier = Modifier.size(8.dp))

        //media


        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .clickable { onClickAction() },
            horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = post.img, contentDescription = null, modifier = Modifier
                        .height(300.dp)
                    .wrapContentWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )
            Spacer(modifier = Modifier.size(8.dp))


            //date
            Text(
                text = "Date: ${post.date}",
                modifier = Modifier.fillMaxWidth(1f),
                fontSize = 12.sp,
                // Adjust the line spacing value as needed
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        //desp
        Text(
            text = post.desp,
            fontSize = 14.sp,
            style = TextStyle(lineHeight = 1.2.em),
            modifier = Modifier.fillMaxWidth(1f)
        )


    }

}


