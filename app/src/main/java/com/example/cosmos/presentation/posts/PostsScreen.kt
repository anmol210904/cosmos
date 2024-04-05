package com.example.cosmos.presentation.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.models.post.CommentModel
import com.example.cosmos.viewModel.PostViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PostsScreen(
    postId: String? = "7212576e-ce4a-4fa0-a9b6-086909f932b9",
    navHostController: NavHostController = rememberNavController()
) {

        val viewModel = koinViewModel<PostViewModel>()

        val post by viewModel.post.observeAsState()

    LaunchedEffect(key1 = Unit) {
        if (postId != null) {
            viewModel.getPost(postId)
        };
    }


    //UI
    if(post != null){
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            //top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {


                AsyncImage(
                    model = post?.userImg, contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.size(24.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(1f),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    post?.let {
                        Text(
                            text = it.username,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(lineHeight = 1.2.em)
                        )
                    }



                    post?.let {
                        Text(
                            text = it.date,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            style = TextStyle(lineHeight = 1.2.em)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert, contentDescription = null,
                    )
                }

            }

            Spacer(modifier = Modifier.size(16.dp))

            //Media


            AsyncImage(
                model = post?.img, contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(8.dp))
            )


            Spacer(modifier = Modifier.size(8.dp))

            //info tab


            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {

                    Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = null)
                }
                Text(text = formatNumber(post!!.likes))

                Spacer(modifier = Modifier.weight(1f))


                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.chat), contentDescription = null ,
                        modifier = Modifier.size(22.dp))
                }
                Text(text = formatNumber(post!!.comments))

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.retweet), contentDescription = null ,
                        modifier = Modifier.size(22.dp))
                }
                Text(text = "32.3k")


            }

            val comments = arrayListOf<CommentModel>()
            for (i in 1..10){
                comments.add(CommentModel())
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(comments){
                    Comment()
                    Divider(modifier = Modifier.fillMaxWidth(1f))
                }

            }


        }

    }


}



@Preview(showBackground = true)
@Composable
fun Comment(commentModel: CommentModel= CommentModel()){

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 8.dp)
    ) {

        //info tab
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {


            AsyncImage(
                model = commentModel.img, contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(
                modifier = Modifier.fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = commentModel.username,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(lineHeight = 1.em)
                )



                Text(
                    text = commentModel.date,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    style = TextStyle(lineHeight = 1.em)
                )
            }

            Spacer(modifier = Modifier.fillMaxWidth(1f))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert, contentDescription = null,
                )
            }
        }



        Text(text = commentModel.content, style = TextStyle(lineHeight = 1.2.em), fontSize = 12.sp)
    }
}



