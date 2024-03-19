package com.example.cosmos.repo

import com.example.cosmos.models.auth.SignUpModel
import com.example.cosmos.models.post.PostModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    suspend fun getPosts(uid :String): ArrayList<PostModel>? {

        var response : ArrayList<PostModel> ? = null
        val func1 = CoroutineScope(Dispatchers.Main).launch {
            Firebase.firestore.collection("posts").whereEqualTo("userUid",uid).get().addOnSuccessListener{
                response = arrayListOf()
                for (i in it.documents){
                    val temp = i.toObject(PostModel ::class.java)
                    if (temp != null) {
                        response!!.add(temp)
                    };
                }


            }.await()
        }
        func1.join()
        return response
    }
}