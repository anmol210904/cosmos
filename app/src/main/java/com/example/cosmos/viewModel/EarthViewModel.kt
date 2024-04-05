package com.example.cosmos.viewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cosmos.models.earthModel.EarthResponseModel
import com.example.cosmos.repo.EarthRepo
import kotlinx.coroutines.launch

class EarthViewModel(private val repo: EarthRepo) : ViewModel() {


    private val _earthData = MutableLiveData<EarthResponseModel?>()
    val earthData : LiveData<EarthResponseModel?>
        get() = _earthData



    fun getData(longitude : String  , latitude : String , date : String){
        viewModelScope.launch{
            _earthData.postValue(repo.getData(longitude,latitude,date));
        }
    }

}