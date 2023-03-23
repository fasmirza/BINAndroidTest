package com.temotion.mirzas.binandroidtest.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.temotion.mirzas.binandroidtest.Model.ImageList
import com.temotion.mirzas.datafetchingmvvmhiltretro.Network.RetroService
import com.temotion.mirzas.datafetchingmvvmhiltretro.Network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel(){

    lateinit var countryLiveData : MutableLiveData<List<ImageList>>

    init {
        countryLiveData = MutableLiveData()
    }
    fun getLiveDataObserver() : MutableLiveData<List<ImageList>> {
        return countryLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO){
            val retroInstance = RetrofitInstance.getRetroInstance()
            val retroService = retroInstance.create(RetroService::class.java)
            val countryData = retroService.getImagelist()

            countryLiveData.postValue(countryData)
        }
    }
}