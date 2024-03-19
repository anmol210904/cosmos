package com.example.cosmos.models.apod

class ApodResponseClass() {
    var date: String = ""
    var explanation: String = ""
    var hdurl: String = ""
    var title: String = ""
    var url: String = ""

    constructor(date: String, explanation: String, hdurl: String, title: String, url: String) : this() {
        this.date = date
        this.explanation = explanation
        this.hdurl = hdurl
        this.title = title
        this.url = url
    }
}
