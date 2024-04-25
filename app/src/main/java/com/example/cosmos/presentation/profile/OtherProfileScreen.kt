package com.example.cosmos.presentation.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.cosmos.navigation.NavItem
import com.example.cosmos.presentation.utils.PostView
import com.example.cosmos.viewModel.ProfileViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun OtherProfileScreen(
    mainNavController: NavHostController = rememberNavController(),
    userId: String = ""
) {


    val viewModel = koinViewModel<ProfileViewModel>()

    LaunchedEffect(key1 = Unit) {
        viewModel.getProfile(userId)
        viewModel.getPosts(userId)
    }

    val posts by viewModel.posts.observeAsState()
    val profile by viewModel.profile.observeAsState()

    val postsScrollState = rememberLazyListState()


    //values


    //UI


    Column(
        modifier = Modifier.fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Top bar

        if (profile != null) {
            Row(
                modifier = Modifier.height(64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    mainNavController.popBackStack()
                }) {

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )

                }

                Text(text = profile!!.username, fontSize = 18.sp)

                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )

                }
            }

            Divider()
            Spacer(modifier = Modifier.size(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(100.dp)
                    .padding(horizontal = 16.dp)
            ) {

                //dp
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.BottomEnd // Align content to the bottom end (i.e., bottom right) of the Box
                ) {
                    AsyncImage(
                        model = profile!!.img,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                            .background(color = Color.Blue),
                        contentScale = ContentScale.Crop

                    )

                }

                Spacer(modifier = Modifier.size(32.dp))

                Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
//                    .fillMaxWidth(1f)
                        .fillMaxHeight(1f)
                ) {
                    Text(text = profile!!.name, fontSize = 18.sp)
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(text = "Posts : ${profile!!.posts}", fontSize = 18.sp)

                }

            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                shape = RoundedCornerShape(0.dp),
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(0.dp)) // Set corner radius to 0 for a rectangle shape
            ) {
                // Button content
                Text(text = "My Posts", fontSize = 18.sp)
            }

            if (posts != null) {


                LazyColumn(state = postsScrollState) {
                    if(posts!= null){
                        items(posts!!) {
                            PostView(it) {
                                mainNavController.navigate(NavItem.Post.screen_route + "/${it.postId}")
                            }
                        }
                    }
                }
            }

        }


    }

}
