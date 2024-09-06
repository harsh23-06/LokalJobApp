package com.example.lokaljobapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobapp.model.JobResponse
import com.example.lokaljobapp.model.JobResult
import com.example.lokaljobapp.repository.JobRepository


class JobsViewModel(private val repository: JobRepository) : ViewModel() {


    private val _jobList = MutableLiveData<List<JobResult>>()
    val jobList: LiveData<List<JobResult>> = _jobList

    private var currentPage = 1

    fun loadJobs() {

        repository.getJobs(currentPage).observeForever { response ->
            response?.let {
                val currentList = _jobList.value ?: emptyList()
                _jobList.postValue((currentList + it.results) as List<JobResult>?)
                currentPage++
            }
        }
    }
}

