package com.example.cosmos.models.post

class CommentModel() {
    var img : String = ""
    var username : String = "anmol0012"
    var content : String  = "this is a dummy comment"
    var date : String = "12-12-2004"


    constructor(img :String , username : String , content : String , date : String ) : this(){
        this.img = img
        this.content  = content
        this.username = username
        this.date = date
    }
}