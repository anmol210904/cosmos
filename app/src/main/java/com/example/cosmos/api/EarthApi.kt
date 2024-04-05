package com.example.cosmos.api

import com.example.cosmos.Utils.Urls
import com.example.cosmos.models.earthModel.EarthResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthApi {

    @GET(Urls.EARTH_IMAGE)
    suspend fun getData(
        @Query("lon") longitude : String,
        @Query("lat") latitude : String ,
        @Query("date") date : String,
        @Query("dim") dim : String = "0.15",
        @Query("api_key") key : String = "DEMO_KEY"
    ) : Response<EarthResponseModel>
}