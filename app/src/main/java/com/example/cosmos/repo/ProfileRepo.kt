package com.example.cosmos.repo

import android.net.Uri
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.models.post.PostModel
import com.google.firebase.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ProfileRepo {



    suspend fun getProfile(uid : String ): SignUpModel? {
        var response : SignUpModel?= null
        val func1 = CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("users").document(uid).get().addOnSuccessListener {
                response = it.toObject(SignUpModel :: class.java)
            }.await()
        }
        func1.join()
        return response

    }

    suspend fun getPosts(uid :String): ArrayList<GetPostModel>? {

        var response : ArrayList<GetPostModel> ? = null
        val func1 = CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").whereEqualTo("userUid",uid).get().addOnSuccessListener{
                response = arrayListOf()
                for (i in it.documents){
                    var temp = i.toObject(GetPostModel ::class.java)

                    if (temp != null) {
                        temp.postId = i.id
                        response!!.add(temp)

                    };
                }


            }.await()
        }
        func1.join()
        return response
    }

    suspend fun editUser(username: String, fullName: String, image: Uri?) : Response<Unit> {
        return try{
            var user =
                com.google.firebase.ktx.Firebase.firestore.collection("users").document(com.google.firebase.ktx.Firebase.auth.uid!!).get().await()
                    .toObject(SignUpModel::class.java)

            if (image != null) {
                val storage = com.google.firebase.Firebase.storage
                val storageRef = storage.reference
                val imagesRef = storageRef.child("images/${UUID.randomUUID()}")
                var downloadUrl = ""

                imagesRef.putFile(image).await()


//        Log.d(TAG, "Step one completed")
                imagesRef.downloadUrl.addOnSuccessListener {
                    downloadUrl = it.toString()


                }.await()
                user!!.img = downloadUrl
            }

            user!!.username = username
            user.name = fullName


            com.google.firebase.ktx.Firebase.firestore.collection("users").document(com.google.firebase.ktx.Firebase.auth.uid!!).set(user).await()
            Response.Success(null)
        }catch (e : Exception){
            Response.Error(e.message.toString())
        }



    }
}