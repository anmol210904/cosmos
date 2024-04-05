package com.example.cosmos.repo

import android.util.Log
import com.example.cosmos.api.EarthApi
import com.example.cosmos.models.earthModel.EarthResponseModel

class EarthRepo(private val api : EarthApi){

    val TAG = "EARTH_REPO"

    suspend fun getData(longitude : String , latitude : String , date : String): EarthResponseModel? {
        Log.d(TAG,"apiCalls")
        val result = api.getData(longitude, latitude, date);
        Log.d(TAG,"apiCalled")

        if(result.body() != null){
            Log.d(TAG,"success")
            return result.body()
        }
        else{
            Log.d(TAG,"failure")

            return null
        }
    }


}