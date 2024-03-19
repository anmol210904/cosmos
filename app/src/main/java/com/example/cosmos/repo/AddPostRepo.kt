package com.example.cosmos.repo

import android.net.Uri
import com.example.cosmos.models.post.PostModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.util.UUID

class AddPostRepo {
    suspend fun addPost(imageUri: Uri, desp: String) {
        val storage = Firebase.storage
        val storageRef = storage.reference
        val imagesRef = storageRef.child("images/${UUID.randomUUID()}")
        var downloadUrl = ""
        imagesRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->

                var downloadUrl: String = ""
                // Image uploaded successfully
                imagesRef.downloadUrl.addOnSuccessListener {
                    downloadUrl = it.toString()

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


                    }



                }


                // You can do something with the download URL, such as saving it to a database
            }
            .addOnFailureListener { exception ->
                // Handle unsuccessful uploads
                exception.printStackTrace()
            }


    }


}