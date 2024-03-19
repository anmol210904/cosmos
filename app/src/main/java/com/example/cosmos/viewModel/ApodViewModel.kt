package com.example.cosmos.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.apod.ApodResponseClass
import com.example.cosmos.repo.ApodRepo
import kotlinx.coroutines.launch

class ApodViewModel(private val repo: ApodRepo): ViewModel() {

    val TAG = "APOD VIEW MODEL"

    val _data = MutableLiveData<ArrayList<ApodResponseClass>?>()
    val data : LiveData<ArrayList<ApodResponseClass>?>
        get() = _data

    init {
        _data.postValue(null)
    }

    fun getData(){


                _data.postValue(null)
                viewModelScope.launch {
                    _data.postValue(repo.getData())
                }

    }

    fun test(){
        Log.d(TAG,"KOIN is workign")
    }

}