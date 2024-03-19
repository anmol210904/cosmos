package com.example.cosmos.models.auth

class SignUpModel() {
    var name: String = ""
    var username: String = ""
    var posts: ArrayList<String> = arrayListOf()
    var UID: String = ""
    var img : String = "https://apod.nasa.gov/apod/image/2401/PlutoTrueColor_NewHorizons_960.jpg"

    constructor(name: String, username: String,  UID: String)  : this() {
        this.name = name
        this.username = username
        this.UID = UID
    }

}
