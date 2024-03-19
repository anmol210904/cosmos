package com.example.cosmos.models.post

class CommentModel() {
    var img : String = ""
    var username : String = ""
    var content : String  = ""

    constructor(img :String , username : String , content : String ) : this(){
        this.img = img
        this.content  = content
        this.username = username
    }
}