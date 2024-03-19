package com.example.cosmos.repo

import com.example.cosmos.models.apod.ApodResponseClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ApodRepo() {

    suspend fun getData(): ArrayList<ApodResponseClass> {
        val data = arrayListOf<ApodResponseClass>()
        val func1 = CoroutineScope(Dispatchers.Main).launch {
            val func1 = Firebase.firestore.collection("apod").orderBy("date",Query.Direction.DESCENDING).get().addOnSuccessListener{
                for(i in it.documents){
                    val temp = i.toObject(ApodResponseClass ::class.java)
                    if (temp != null) {
                        data.add(temp)
                    };
                }
            }
            func1.await()
        }

        func1.join()
        return data


    }

}