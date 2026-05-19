package com.example.viecsv.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class Job(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val company: String,

    val salary: String,

    val category: String
)