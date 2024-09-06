package com.example.lokaljobapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lokaljobapp.repository.JobRepository

class JobsViewModelFactory(private val repository: JobRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobsViewModel::class.java)) {
            return JobsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}