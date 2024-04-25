package com.example.cosmos.repo


import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.auth.SignUpModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthRepo {


    suspend fun login(email: String, pass: String): Response<String> {

        return try {
            Firebase.auth.signInWithEmailAndPassword(email, pass).await()

            Response.Success("Success")
        } catch (e: Exception) {
            Response.Error(e.toString())
        }
    }

    suspend fun signUp(
        email: String,
        pass: String,
        username: String,
        name: String
    ): Response<String> {

        return try {

            Firebase.auth.createUserWithEmailAndPassword(email, pass).await()
            if(Firebase.auth.currentUser != null){
                Firebase.firestore.collection("users")
                    .document(Firebase.auth.currentUser?.uid.toString()).set(
                        SignUpModel(name, username, Firebase.auth.currentUser?.uid.toString())
                    ).await()
            }

            Response.Success("Success")
        } catch (e: Exception) {
            Response.Error(e.toString())
        }


    }
}