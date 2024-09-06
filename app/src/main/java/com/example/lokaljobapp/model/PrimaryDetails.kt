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
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Place)
        parcel.writeString(Salary)
        parcel.writeString(jobType)
        parcel.writeString(experience)
        parcel.writeString(feesCharged)
        parcel.writeString(qualification)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PrimaryDetails> {
        override fun createFromParcel(parcel: Parcel): PrimaryDetails {
            return PrimaryDetails(parcel)
        }

        override fun newArray(size: Int): Array<PrimaryDetails?> {
            return arrayOfNulls(size)
        }
    }
}
