package com.digikraft.bikestation.model.bike

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: PropertiesX,
    val type: String
) : Parcelable