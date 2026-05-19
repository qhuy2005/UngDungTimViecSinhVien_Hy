package com.example.viecsv.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viecsv.data.Job
import com.example.viecsv.data.JobDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobViewModel(
    private val dao: JobDao
) : ViewModel() {

    val jobs = dao.getAllJobs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun addJob(job: Job) {

        viewModelScope.launch {
            dao.insert(job)
        }
    }

    fun deleteJob(job: Job) {

        viewModelScope.launch {
            dao.delete(job)
        }
    }

    fun updateJob(job: Job) {

        viewModelScope.launch {
            dao.update(job)
        }
    }
}