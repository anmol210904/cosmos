package com.example.cosmos.models.post

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class PostModel() {
    var img: String = ""
    var desp: String = ""
    var username: String = ""
    var userImg: String = ""
    var userUid: String = ""

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