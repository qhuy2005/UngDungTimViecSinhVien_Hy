package com.example.viecsv.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert
    suspend fun insert(job: Job)

    @Update
    suspend fun update(job: Job)

    @Delete
    suspend fun delete(job: Job)

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<Job>>
}