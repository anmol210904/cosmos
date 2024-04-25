package com.example.cosmos.repo

import com.example.cosmos.api.MarsRoverApi
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.marsRover.MarsRoverResponseModel

class MarsRoverRepo(private val api : MarsRoverApi) {

    suspend fun getData(date : String): Response<MarsRoverResponseModel> {

        return try {
            Response.Success(api.getMarsPhotos(date))
        }catch (e : Exception){
            Response.Error(e.message.toString())
        }
    }
}