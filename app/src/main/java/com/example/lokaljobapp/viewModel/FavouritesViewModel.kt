package com.example.lokaljobapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lokaljobapp.db.JobEntity
import com.example.lokaljobapp.repository.JobRepository

class FavoritesViewModel(private val repository: JobRepository) : ViewModel() {

    val favoriteJobs: LiveData<List<JobEntity>> = repository.getFavoriteJobs()


}