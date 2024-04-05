package com.example.cosmos.models.post

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate

class PostModel() : Serializable {
    var img: String = "https://apod.nasa.gov/apod/image/2401/Slim_jaxa_960.jpg"
    var desp: String = "This is img of rover landing on mars."
    var username: String = "anmolsingla001"
    var userImg: String = "https://apod.nasa.gov/apod/image/2401/PlutoTrueColor_NewHorizons_960.jpg"
    var userUid: String = "ngibgbhetrbghbe"
    var likes : Int = 0;
    var comments : Int = 0
    var retweets : Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    var date: String = LocalDate.now().toString()


//    var comments  = arrayListOf<CommentModel>()

    constructor(
        img: String,
        desp: String,
        username: String,
        userImg: String,
        userUid: String
    ) : this() {
        this.desp = desp
        this.img = img
        this.username = username
        this.userImg = userImg
        this.userUid = userUid
    }



}