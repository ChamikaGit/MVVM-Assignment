package com.digikraft.bikestation.model.bike

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PropertiesX(
    val bike_racks: String,
    val bikes: String,
    val free_racks: String,
    val label: String,
    val updated: String
) : Parcelable