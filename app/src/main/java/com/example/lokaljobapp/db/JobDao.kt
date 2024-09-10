package com.example.lokaljobapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JobDao {
    @Insert
    suspend fun insertJob(job: JobEntity)

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    fun getJob(jobId: String): LiveData<JobEntity>

    @Query("UPDATE jobs SET isFavorite = :isFavorite WHERE id = :jobId")
    suspend fun updateFavorite(jobId: Int, isFavorite: Int)

    @Query("SELECT * FROM jobs WHERE isFavorite = 1")
    fun getFavoriteJobs(): LiveData<List<JobEntity>>
}
