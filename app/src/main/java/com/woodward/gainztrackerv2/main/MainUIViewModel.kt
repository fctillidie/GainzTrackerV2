package com.woodward.gainztrackerv2.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.woodward.gainztrackerv2.database.ExerciseDatabase
import com.woodward.gainztrackerv2.repository.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainUIViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: ExerciseRepository

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        val dataSource = ExerciseDatabase.getInstance(application)
        repository = ExerciseRepository.getInstance(dataSource)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}