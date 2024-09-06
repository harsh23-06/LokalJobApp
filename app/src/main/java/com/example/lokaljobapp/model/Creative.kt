package com.example.lokaljobapp.model

import com.google.gson.annotations.SerializedName

data class Creative(
    val file: String,
    @SerializedName("thumb_url")
    val thumbUrl: String,
    @SerializedName("creative_type")
    val creativeType: Int
)