package com.woodward.gainztrackerv2.exerciseselection.exercisetypeselection

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woodward.gainztrackerv2.database.ExerciseDatabase
import com.woodward.gainztrackerv2.database.entity.Category
import com.woodward.gainztrackerv2.database.entity.ExerciseType
import com.woodward.gainztrackerv2.repositories.ExerciseRepository
import com.woodward.gainztrackerv2.repositories.ExerciseTypeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ExerciseTypeViewModel @ViewModelInject constructor (val repository: ExerciseTypeRepository) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _catID = MutableLiveData<Int>()

    /**
     * NEEDS CHANGING
     */
    val exerciseTypeList : LiveData<List<ExerciseType?>> = repository.getExerciseTypeList(_catID.value!!)


    private val _navigateToExerciseDetails = MutableLiveData<Boolean>()
    val navigateToExerciseDetails: LiveData<Boolean>
        get() = _navigateToExerciseDetails

    /**
     * May not be needed for this portion of the application
     *
     * Unless the date is needed to be passed in here
     */

    suspend fun deleteExerciseType(exerciseType: ExerciseType) = viewModelScope.launch (Dispatchers.IO) {
        repository.deleteExerciseType(exerciseType)
    }

    fun doneNavigating() {
        _navigateToExerciseDetails.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}