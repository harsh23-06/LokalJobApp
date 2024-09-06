package com.example.lokaljobapp.model


import android.os.Parcel
import android.os.Parcelable

data class JobResponse(
    val results: List<JobResult>
)

data class JobResult(
    val id: Int,
    val title: String,
    val type: Int,
    val primary_details: PrimaryDetails?,
    val fee_details: FeeDetails?,
    val job_tags: List<JobTag>?,
    val job_type: Int,
    val job_category_id: Int,
    val qualification: Int,
    val experience: Int,
    val shift_timing: Int,
    val job_role_id: Int,
    val salary_max: Int,
    val salary_min: Int,
    val city_location: Int,
    val locality: Int,
    val premium_till: String,
    val content: String,
    val company_name: String,
    val advertiser: Int,
    val button_text: String,
    val custom_link: String,
    val amount: String,
    val views: Int,
    val shares: Int,
    val fb_shares: Int,
    val is_bookmarked: Boolean,
    val is_applied: Boolean,
    val is_owner: Boolean,
    val updated_on: String,
    val whatsapp_no: String,
    val contact_preference: ContactPreference?,
    val created_on: String,
    val is_premium: Boolean,
    val creatives: List<Creative>?,
    val locations: List<Location>?,
    val status: Int,
    val expire_on: String,
    val job_hours: String,
    val openings_count: Int,
    val job_role: String,
    val other_details: String,
    val job_category: String,
    val num_applications: Int,
    val enable_lead_collection: Boolean,
    val is_job_seeker_profile_mandatory: Boolean,
    val translated_content: TranslatedContent?,
    val job_location_slug: String,
    val fees_text: String?,
    val question_bank_id: Int?,
    val screening_retry: Int?,
    val should_show_last_contacted: Boolean,
    val fees_charged: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readParcelable(PrimaryDetails::class.java.classLoader),
        parcel.readParcelable(FeeDetails::class.java.classLoader),
        parcel.createTypedArrayList(JobTag.CREATOR),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt() == 1,
        parcel.readInt() == 1,
        parcel.readInt() == 1,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(ContactPreference::class.java.classLoader),
        parcel.readString() ?: "",
        parcel.readInt() == 1,
        parcel.createTypedArrayList(Creative.CREATOR),
        parcel.createTypedArrayList(Location.CREATOR),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt() == 1,
        parcel.readInt() == 1,
        parcel.readParcelable(TranslatedContent::class.java.classLoader),
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt() == 1,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeParcelable(primary_details, flags)
        parcel.writeParcelable(fee_details, flags)
        parcel.writeTypedList(job_tags)
        parcel.writeInt(job_type)
        parcel.writeInt(job_category_id)
        parcel.writeInt(qualification)
        parcel.writeInt(experience)
        parcel.writeInt(shift_timing)
        parcel.writeInt(job_role_id)
        parcel.writeInt(salary_max)
        parcel.writeInt(salary_min)
        parcel.writeInt(city_location)
        parcel.writeInt(locality)
        parcel.writeString(premium_till)
        parcel.writeString(content)
        parcel.writeString(company_name)
        parcel.writeInt(advertiser)
        parcel.writeString(button_text)
        parcel.writeString(custom_link)
        parcel.writeString(amount)
        parcel.writeInt(views)
        parcel.writeInt(shares)
        parcel.writeInt(fb_shares)
        parcel.writeInt(if (is_bookmarked) 1 else 0)
        parcel.writeInt(if (is_applied) 1 else 0)
        parcel.writeInt(if (is_owner) 1 else 0)
        parcel.writeString(updated_on)
        parcel.writeString(whatsapp_no)
        parcel.writeParcelable(contact_preference, flags)
        parcel.writeString(created_on)
        parcel.writeInt(if (is_premium) 1 else 0)
        parcel.writeTypedList(creatives)
        parcel.writeTypedList(locations)
        parcel.writeInt(status)
        parcel.writeString(expire_on)
        parcel.writeString(job_hours)
        parcel.writeInt(openings_count)
        parcel.writeString(job_role)
        parcel.writeString(other_details)
        parcel.writeString(job_category)
        parcel.writeInt(num_applications)
        parcel.writeInt(if (enable_lead_collection) 1 else 0)
        parcel.writeInt(if (is_job_seeker_profile_mandatory) 1 else 0)
        parcel.writeParcelable(translated_content, flags)
        parcel.writeString(job_location_slug)
        parcel.writeString(fees_text)
        parcel.writeValue(question_bank_id)
        parcel.writeValue(screening_retry)
        parcel.writeInt(if (should_show_last_contacted) 1 else 0)
        parcel.writeInt(fees_charged)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<JobResult> {
        override fun createFromParcel(parcel: Parcel): JobResult {
            return JobResult(parcel)
        }

        override fun newArray(size: Int): Array<JobResult?> {
            return arrayOfNulls(size)
        }
    }
}
