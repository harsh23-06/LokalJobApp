package com.example.lokaljobapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lokaljobapp.api.JobListService
import com.example.lokaljobapp.api.RetrofitInstance
import com.example.lokaljobapp.db.JobDao
import com.example.lokaljobapp.db.JobEntity
import com.example.lokaljobapp.model.JobResponse
import com.example.lokaljobapp.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobRepository(private val jobDao: JobDao) {

     private val apiService = RetrofitInstance.api

     fun getJobs(page: Int): LiveData<JobResponse> {
          val jobResponseLiveData = MutableLiveData<JobResponse>()
          apiService.getJobs(page).enqueue(object : Callback<JobResponse> {
               override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                    if (response.isSuccessful) {
                         jobResponseLiveData.postValue(response.body())
                    }
               }

               override fun onFailure(call: Call<JobResponse>, t: Throwable) {
                    // Handle error
               }
          })
          return jobResponseLiveData
     }
     suspend fun updateJobFavorite(job: JobEntity) {
          jobDao.updateFavorite(job.id, job.isFavorite)

     }
     suspend fun insertJob(job: JobEntity) {
          jobDao.insertJob(job)
     }

     fun getFavoriteJobs(): LiveData<List<JobEntity>> {
          return jobDao.getFavoriteJobs()
     }
     fun getAllJobs():LiveData<List<JobEntity>> {
          return jobDao.getAllJobs()
     }
}

