package com.example.cosmos.repo

import android.net.Uri
import android.util.Log
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.PostModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class AddPostRepo {
    suspend fun addPost(imageUri: Uri, desp: String): Response<String> {

        val TAG = "ADD_POST_REPO"
        return try {
            val storage = Firebase.storage
            val storageRef = storage.reference
            val imagesRef = storageRef.child("images/${UUID.randomUUID()}")
            var downloadUrl = ""
            imagesRef.putFile(imageUri).await()

            Log.d(TAG, "Step one completed")
            imagesRef.downloadUrl.addOnSuccessListener {
                downloadUrl = it.toString()




            }.await()
            Log.d(TAG, "Step 2 completed")

            val uid = UUID.randomUUID().toString()
            Firebase.firestore.collection("users").document(Firebase.auth.uid.toString())
                .get().addOnSuccessListener {
                    Firebase.firestore.collection("posts")
                        .document(uid).set(
                            PostModel(
                                downloadUrl,
                                desp,
                                it.get("username").toString(),
                                it.get("img").toString(),
                                Firebase.auth.uid.toString()
                            )
                        )


                }.await()
            Log.d(TAG, "Step 3 completed")
            var temp : SignUpModel = SignUpModel()
            Firebase.firestore.collection("users").document(Firebase.auth.uid!!).get().addOnSuccessListener {
                temp = it.toObject(SignUpModel :: class.java)!!

            }.await()

            temp.posts++

            Firebase.firestore.collection("users").document(Firebase.auth.uid!!).set(temp).await()

            Response.Success<String>("success")
        }catch (e : Exception){
            Response.Error(e.message.toString())
        }


    }




}