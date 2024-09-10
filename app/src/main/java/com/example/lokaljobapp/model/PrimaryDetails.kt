package com.example.lokaljobapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PrimaryDetails(
    val Place: String?,
    val Salary: String?,
    @SerializedName("Job_Type")
    val jobType: String?,
    @SerializedName("Experience")
    val experience: String?,
    @SerializedName("Fees_Charged")
    val feesCharged: String?,
    @SerializedName("Qualification")
    val qualification: String?
)