package com.example.lokaljobapp.model

import com.google.gson.annotations.SerializedName

data class ContactPreference(
    val preference: Int,
    @SerializedName("whatsapp_link")
    val whatsappLink: String,
    @SerializedName("preferred_call_start_time")
    val preferredCallStartTime: String,
    @SerializedName("preferred_call_end_time")
    val preferredCallEndTime: String
)
