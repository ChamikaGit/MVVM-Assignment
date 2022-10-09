package com.digikraft.bikestation.data.repository

import com.digikraft.bikestation.data.network.BikeDetailsAPI
import com.digikraft.bikestation.data.repository.base.BaseRepository
import com.digikraft.bikestation.model.bike.BikeDetailsResponse
import com.digikraft.bikestation.utils.Resource
import javax.inject.Inject

class BikeDetailsRepository @Inject constructor(private val bikeDetailsAPI: BikeDetailsAPI) :
    BaseRepository() {

    suspend fun getBikeDetails(type: String, co: String): Resource<BikeDetailsResponse> {
        val response = bikeDetailsAPI.getBikeDetails(mType = type, co = co)
        return safeApiCall { response }
    }

}