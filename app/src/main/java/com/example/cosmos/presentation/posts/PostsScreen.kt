package com.example.cosmos.presentation.posts

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.R
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.post.CommentModel
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.viewModel.PostViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PostsScreen(
    postId: String? = "7212576e-ce4a-4fa0-a9b6-086909f932b9",
    navHostController: NavHostController = rememberNavController()
) {

    val viewModel = koinViewModel<PostViewModel>()

    val post by viewModel.post.observeAsState()


    val commentsList by viewModel.loadCommentResponse.observeAsState()


    var commentText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        if (postId != null) {
            Log.d("Post_detail",postId)
        }
        if (postId != null) {
            viewModel.getPost(postId)
            viewModel.loadComments(postId)
//            viewModel.addLike(postId)
        }


    }

    var commentLoading by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    val popUp = remember {
        mutableStateOf(false)
    }

    val commentResponse by viewModel.commentResponse.observeAsState()
    commentResponse?.let {
        when(it){
            is Response.Error -> {
                Toast.makeText(LocalContext.current, it.errorMessage, Toast.LENGTH_SHORT).show()
                commentLoading = false
            }
            is Response.Loading -> {
                commentLoading = true
            }
            is Response.Success -> {
                commentLoading = false
                commentText = ""

            }
            else -> {

            }
        }
    }

    post?.let {
        when(it){
            is Response.Error -> {

                navHostController.popBackStack()
            }
            is Response.Loading -> {
                isLoading = true
            }
            is Response.Success -> {
                Log.d("Detail_Screen",it.data?.likes.toString()  )
                isLoading = false
            }
        }
    }



    //UI
    if (isLoading == false) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            //top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(48.dp)
                ,
                verticalAlignment = Alignment.CenterVertically

            ) {


                AsyncImage(
                    model = post!!.data!!.userImg, contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop

                    )
                Spacer(modifier = Modifier.size(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .clickable {
                            navHostController.navigate(NavItem.OtherProfile.screen_route + "/${post!!.data!!.userUid}")
                        },
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    post?.let {
                        Text(
                            text = it.data!!.username,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(lineHeight = 1.2.em),
                            fontFamily = FontFamily.Serif
                        )
                    }


                    post?.let {
                        Text(
                            text = it.data!!.date,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            style = TextStyle(lineHeight = 1.2.em),
                            fontFamily = FontFamily.Serif

                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))


                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        popUp.value = true
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

            }
            ReportPopUp(onclick = { popUp.value = false
                                  navHostController.popBackStack()}, open = popUp)


            Spacer(modifier = Modifier.size(16.dp))

            //Media


            AsyncImage(
                model = post?.data?.img, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(8.dp))
            )


            Spacer(modifier = Modifier.size(8.dp))

            //info tab


            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /*TODO*/ }) {

                    Icon(painter = painterResource(id = R.drawable.viewa), contentDescription = null,
                        modifier = Modifier.size(22.dp))
                }

                post?.data?.let{
                    Text(
                        text =   formatNumber(it.likes) , fontFamily = FontFamily.Serif
                    )
                }


                Spacer(modifier = Modifier.weight(1f))


                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat), contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Text(
                    text = formatNumber(post!!.data!!.comments), fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = {
                    viewModel.repost(post!!.data!!)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.retweet),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                }



            }



            LazyColumn(modifier = Modifier.fillMaxWidth()) {


                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = post?.data!!.userImg, contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )


                        val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent
                        )


                        OutlinedTextField(
                            value = commentText,
                            onValueChange = {
                                commentText = it
                            },
                            colors = outlinedTextFieldColors,
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp),
                            textStyle = TextStyle(
                                fontSize = 12.sp, fontFamily = FontFamily.Serif
                            ),
                            placeholder = {
                                Text(
                                    text = "Add a comment",
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Serif
                                )
                            }
                        )

//                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = {
                            val comment = CommentModel(
                                post!!.data!!.userImg, username = post!!.data!!.username, content = commentText,
                                date = Date.from(
                                    Instant.now()
                                ).toString(),
                            )
                            if (postId != null) {
                                viewModel.addComment(postId, comment)
                            }
                        }) {
                            if(!commentLoading){
                                Icon(
                                    painter = painterResource(id = R.drawable.post),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }else{
                                CircularProgressIndicator(modifier = Modifier.size(32.dp), color = MaterialTheme.colorScheme.primary)
                            }
                        }

                    }
                    Divider(modifier = Modifier.fillMaxWidth(1f))
                }

                if (commentsList?.data != null) {
                    items(commentsList?.data!!) {
                        Comment(it)
                    }
                }

            }


        }

    }


}


@Preview(showBackground = true)
@Composable
fun Comment(commentModel: CommentModel = CommentModel()) {

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
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light,
                    style = TextStyle(
                        lineHeight = 1.em,
                        fontFamily = FontFamily.Serif
                    )
                )
            }

            Spacer(modifier = Modifier.fillMaxWidth(1f))

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.MoreVert, contentDescription = null,
                )
            }
        }



        Text(
            text = commentModel.content,
            style = TextStyle(lineHeight = 1.2.em),
            fontSize = 12.sp,
            fontFamily = FontFamily.Serif
        )
    }
}



