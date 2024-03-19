package com.example.cosmos.repo

import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.cosmos.models.post.GetPostModel
import com.example.cosmos.models.post.PostModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GetPostRepo() {

    suspend fun getPosts(): ArrayList<PostModel> {
        val response = arrayListOf<PostModel>()
        val func = CoroutineScope(Dispatchers.IO).launch {
            Firebase.firestore.collection("posts").limit(10).get().addOnSuccessListener {
                for(i in it.documents){
                    val temp = i.toObject(PostModel ::class.java)
                    if (temp != null) {
                        response.add(temp)
                    }
                }
            }.await()
        }

        func.join()
        return response


    }
}