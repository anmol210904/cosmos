package com.example.cosmos.repo

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.DisposableEffect
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.CommentModel
import com.example.cosmos.models.post.GetPostModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class PostRepo {

    suspend fun getPost(postId: String): Response<GetPostModel> {

        return try {
            var snapshot = Firebase.firestore.collection("posts").document(postId).get().await()
            var temp = snapshot.toObject(GetPostModel::class.java)
            if (temp != null) {
                Response.Success(temp)
            } else {
                Response.Error("No data available")
            }
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }

    }


    suspend fun addLike(postId: String) {
        try {
            var snapshot = Firebase.firestore.collection("posts").document(postId).get().await()
            var temp = snapshot.toObject(GetPostModel::class.java)
            temp?.likes = temp?.likes!! + 1
            Firebase.firestore.collection("posts").document(postId).set(temp).await()
        } catch (_: Exception) {

        }
    }

    suspend fun addComment(
        postId: String,
        commentModel: CommentModel
    ): Response<Unit> {

        return try {
            Firebase.firestore.collection("posts").document(postId).collection("comments")
                .document(UUID.randomUUID().toString()).set(commentModel).await()
            var documentSnapshot =
                Firebase.firestore.collection("posts").document(postId).get().await()
            var temp = documentSnapshot.toObject(GetPostModel::class.java)
            temp?.comments = temp?.comments!! + 1
            Firebase.firestore.collection("posts").document(postId).set(temp).await()
            Response.Success()
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }

    }

    suspend fun loadComments(
        postId: String
    ): Response<ArrayList<CommentModel>> {

        return try {

            val comments = arrayListOf<CommentModel>()

            val func = CoroutineScope(Dispatchers.IO).launch {
                Firebase.firestore.collection("posts").document(postId).collection("comments").get()
                    .addOnSuccessListener {
                        for (i in it.documents) {
                            i.toObject(CommentModel::class.java)?.let { it1 -> comments.add(it1) }
                        }
                    }.await()
            }

            func.join()

            Response.Success(comments)
        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }

    suspend fun retweet(postModel: GetPostModel): Response<String> {
        postModel.userUid = Firebase.auth.uid!!
        return try {
            var user: SignUpModel = SignUpModel()
            Firebase.firestore.collection("users").document(Firebase.auth.uid!!).get()
                .addOnSuccessListener {
                    user = it.toObject(SignUpModel::class.java)!!
                }.await()
            postModel.username = user.username
//            postModel.date =
            Firebase.firestore.collection("posts").document(UUID.randomUUID().toString())
                .set(postModel).await()
            Response.Success("Success")

        } catch (e: Exception) {
            Response.Error(e.message.toString())
        }
    }




}