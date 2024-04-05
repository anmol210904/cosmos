package com.example.cosmos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.marsRover.MarsRoverResponseModel
import com.example.cosmos.repo.MarsRoverRepo
import kotlinx.coroutines.launch

class MarsRoverViewModel(private val repo: MarsRoverRepo) : ViewModel() {

    private val _data = MutableLiveData<MarsRoverResponseModel>()
    val data : LiveData<MarsRoverResponseModel>
        get() = _data



    fun getDate(date : String){
        viewModelScope.launch{
            _data.postValue(repo.getData(date))
        }
    }
}