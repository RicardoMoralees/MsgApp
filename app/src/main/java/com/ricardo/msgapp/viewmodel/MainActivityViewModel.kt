package com.ricardo.msgapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardo.msgapp.model.Digimon
import com.ricardo.msgapp.model.DigimonResponse
import com.ricardo.msgapp.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<DigimonResponse>? = null

    fun getDigimon(nombreDigimon: String) : LiveData<DigimonResponse>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall(nombreDigimon)
        return servicesLiveData
    }

}
