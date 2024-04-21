package com.example.cosmos.repo

import android.util.Log
import com.example.cosmos.api.EarthApi
import com.example.cosmos.api.resource.Response
import com.example.cosmos.models.earthModel.EarthResponseModel


class EarthRepo(private val api : EarthApi){

    val TAG = "EARTH_REPO"

    suspend fun getData(longitude : String , latitude : String , date : String): Response<EarthResponseModel> {


        return try {

            val result = api.getData(longitude, latitude, date)
            Log.d(TAG, result.toString())
            Response.Success(result)
        } catch (e : Exception){
            Log.d(TAG,"in catch block")
            Response.Error<EarthResponseModel>(e.message.toString())
        }
    }


}