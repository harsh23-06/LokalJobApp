package com.example.lokaljobapp.model

import com.google.gson.annotations.SerializedName

data class V3Detail(
    @SerializedName("field_key")
    val fieldKey: String,
    @SerializedName("field_name")
    val fieldName: String,
    @SerializedName("field_value")
    val fieldValue: String
)
