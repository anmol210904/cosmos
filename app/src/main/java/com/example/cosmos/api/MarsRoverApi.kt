package com.example.cosmos.api

import com.example.cosmos.Utils.Urls
import com.example.cosmos.models.marsRover.MarsRoverResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsRoverApi {

    @GET(Urls.MARS_ROVER_PHOTOS)
    suspend fun getMarsPhotos(
        @Query("earth_date")date : String ,
        @Query("api_key") apiKey : String= Urls.API_KEY
    ) : Response<MarsRoverResponseModel>
}