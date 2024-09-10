package com.example.lokaljobapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobapp.db.JobEntity
import com.example.lokaljobapp.model.JobResult
import com.example.lokaljobapp.repository.JobRepository
import kotlinx.coroutines.launch

class JobsViewModel(private val repository: JobRepository) : ViewModel() {

    private val _jobList = MutableLiveData<List<JobResult>>()
    val jobList: LiveData<List<JobResult>> = _jobList

    private var currentPage = 1
    var isLoading = false
    var hasMoreItems = true // Flag to indicate if more jobs are available

    fun loadJobs() {
        if (isLoading || !hasMoreItems) isLoading =
            false // Prevent loading if no more items or already loading

        isLoading = true
        repository.getJobs(currentPage).observeForever { response ->
            response?.let {
                val currentList = _jobList.value ?: emptyList()
                val newJobs = it.results
                _jobList.postValue(currentList + newJobs)

                // If no new jobs are returned, assume it's the last page
                hasMoreItems = newJobs.isNotEmpty()
                if (hasMoreItems) currentPage++ // Increment the page if more jobs are available
            }
            isLoading = false
        }
    }

    fun updateJobFavorite(job: JobResult, isFavorite: Int) {
        viewModelScope.launch {
            val jobEntity = JobEntity(
                id = job.id,
                title = job.title,
                companyName = job.company_name,
                location = job.primary_details?.Place,
                salary = job.primary_details?.Salary,
                contact = job.whatsapp_no,
                isFavorite = isFavorite
            )
            Log.e("jobentity","$jobEntity")
            repository.updateJobFavorite(jobEntity)
            repository.insertJob(jobEntity)
        }
    }

}
