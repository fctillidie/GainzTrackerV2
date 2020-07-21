package com.woodward.gainztrackerv2.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.woodward.gainztrackerv2.database.dao.WeightedExerciseDao
import com.woodward.gainztrackerv2.database.entity.WeightedExerciseData
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject


//Object to declare it as a singleton
class ExerciseRepository @Inject constructor(val dao: WeightedExerciseDao) {

    /**
     * Coroutine to insert the data into database
     */
    @WorkerThread //Used to not interrupt the main UI thread
    suspend fun insertWeightExerciseData(weightData: WeightedExerciseData) {
        Timber.d("Insert DB called")
        dao.Insert(weightData)
    }

    /**
     * Coroutine to insert data into database
     */
    @WorkerThread
    suspend fun updateWeightExerciseData(weightData: WeightedExerciseData) {
        Timber.d("Update DB called")
        dao.Update(weightData)
    }

    /**
     * Coroutine to delete singular pieces of data from the db
     */
    @WorkerThread
    suspend fun deleteWeightExerciseData(weightData: WeightedExerciseData) {
        Timber.d("Delete DB called")
        dao.Delete(weightData)
    }

    /**
     * Coroutine to delete multiple pieces of data from the db
     */
    @WorkerThread
    suspend fun deleteListOFWeightExerciseData(exerciseName: String, date: String){
        Timber.d("DeleteList DB called")
        dao.DeleteMultipleData(exerciseName, date)
    }

    @WorkerThread
    fun getNameAndSetsForDateMainUI (date: String) : LiveData<List<WeightedExerciseData?>> {
        Timber.i("Call for Main UI Recycler View -> name and date info")
        return dao.GetNameAndSetsForDate(date)
    }
}