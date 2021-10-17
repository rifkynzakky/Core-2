package com.example.core2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (val name: String, val auth : String,
                val rating : Float) : Parcelable {
                }