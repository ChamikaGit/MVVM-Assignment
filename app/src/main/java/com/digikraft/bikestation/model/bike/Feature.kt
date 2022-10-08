package com.digikraft.bikestation.model.bike

data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: PropertiesX,
    val type: String
)