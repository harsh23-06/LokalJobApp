package com.example.lokaljobapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val companyName: String?,
    val location: String?,
    val salary: String?,
    val contact: String?,
    val isFavorite: Int
)
