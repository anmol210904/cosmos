package com.example.cosmos.repo

import com.example.cosmos.api.MarsRoverApi
import com.example.cosmos.models.marsRover.MarsRoverResponseModel
import retrofit2.Response

class MarsRoverRepo(private val api : MarsRoverApi) {

    suspend fun getData(date : String): MarsRoverResponseModel? {
        val result = api.getMarsPhotos(date)
        if(result != null){
            return result.body()
        }
        else{
            return null
        }
    }
}