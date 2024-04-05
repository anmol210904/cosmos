package com.example.cosmos.models.earthModel

data class EarthResponseModel(
    val date: String,
    val id: String,
    val resource: Resource,
    val service_version: String,
    val url: String
)