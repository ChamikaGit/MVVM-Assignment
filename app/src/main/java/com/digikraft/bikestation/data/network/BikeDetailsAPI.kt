package com.digikraft.bikestation.data.network

import com.digikraft.bikestation.model.bike.BikeDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BikeDetailsAPI {

    @GET("mim/plan/map_service.html")
    suspend fun getBikeDetails(
        @Query("mtype") mType: String,
        @Query("co") co: String,
    ): Response<BikeDetailsResponse>
}