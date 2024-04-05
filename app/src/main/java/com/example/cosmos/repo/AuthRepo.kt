package com.example.cosmos.repo


import com.example.cosmos.models.auth.SignUpModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthRepo {


    suspend fun login(email: String, pass: String): String {

        try {
            var response = "Error"
            val func1 = CoroutineScope(Dispatchers.IO).launch {
                val func2 = Firebase.auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener {
                    response = "Success"
                }.addOnFailureListener {
                    response = it.toString()
                }

                func2.await()


            }
            func1.join()
            return response
        }
        catch (e:Exception){
            return e.toString()
        }
    }

    suspend fun signUp(email: String, pass: String, username: String, name: String): String {
        var resposne = "Error";
        val func1 = CoroutineScope(Dispatchers.Main).launch {
            val func2 =
                Firebase.auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
                    Firebase.firestore.collection("users")
                        .document(Firebase.auth.currentUser?.uid.toString()).set(
                            SignUpModel(name , username,Firebase.auth.currentUser?.uid.toString())
                        )

                    resposne = "Success"
                }.addOnFailureListener{
                    resposne = it.toString()
                }
            func2.await()

        }
        func1.join()

        return resposne
    }
}