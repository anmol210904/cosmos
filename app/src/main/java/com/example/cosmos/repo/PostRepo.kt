package com.example.cosmos.repo

import androidx.compose.runtime.DisposableEffect
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.post.CommentModel
import com.example.cosmos.models.post.GetPostModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class PostRepo() {

    suspend fun getPost(postId: String): GetPostModel {
        var temp = GetPostModel();
        CoroutineScope(Dispatchers.IO).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnCompleteListener {
                if (it.isSuccessful) {

                    temp = it.result.toObject(GetPostModel::class.java)!!

                }
            }.await()
        }.join()
        return temp;

    }

    suspend fun addLike(postId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnSuccessListener {
                var likes: Long = it.get("likes").toString().toLong()
                likes++;
                Firebase.firestore.collection("posts").document(postId).update("likes", likes);

            }
        }
    }

    suspend fun removeLike(postId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").document(postId).get().addOnSuccessListener {
                var likes: Long = it.get("likes").toString().toLong()
                likes--;
                Firebase.firestore.collection("posts").document(postId).update("likes", likes);

            }
        }
    }


    suspend fun addComment(
        postId: String,
        commentModel: CommentModel
    ): Response<Unit> {

        return try {
            CoroutineScope(Dispatchers.IO).launch {
                Firebase.firestore.collection("posts").document(postId).collection("comments")
                    .document(UUID.randomUUID().toString()).set(commentModel)
            }
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


}