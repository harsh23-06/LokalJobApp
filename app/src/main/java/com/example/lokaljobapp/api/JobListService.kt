package com.example.lokaljobapp.api

import com.example.lokaljobapp.model.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


interface JobListService {
    @GET("common/jobs")
    fun getJobs(
        @Query("page") page: Int
    ): Call<JobResponse>
}

