package com.digikraft.bikestation.model.bike

data class BikeDetailsResponse(
    val crs: Crs,
    val features: List<Feature>,
    val type: String
)