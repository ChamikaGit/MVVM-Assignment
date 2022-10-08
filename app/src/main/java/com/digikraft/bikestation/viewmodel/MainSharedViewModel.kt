package com.digikraft.bikestation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digikraft.bikestation.data.repository.BikeDetailsRepository
import com.digikraft.bikestation.model.bike.BikeDetailsResponse
import com.digikraft.bikestation.utils.Resource
import com.digikraft.bikestation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(private val bikeDetailsRepository: BikeDetailsRepository) : ViewModel() {

    private val _bikeDetailsResponseLiveData = SingleLiveEvent<Resource<BikeDetailsResponse>>()
    val bikeDetailsResponseLiveData : LiveData<Resource<BikeDetailsResponse>> get() = _bikeDetailsResponseLiveData

    fun loadBikeData(type : String,co: String){
        viewModelScope.launch {
            _bikeDetailsResponseLiveData.postValue(Resource.Loading())
            _bikeDetailsResponseLiveData.postValue(bikeDetailsRepository.getBikeDetails(type = type, co = co))
        }

    }
}