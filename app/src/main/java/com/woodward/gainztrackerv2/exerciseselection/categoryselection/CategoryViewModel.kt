package com.woodward.gainztrackerv2.exerciseselection.categoryselection

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woodward.gainztrackerv2.database.ExerciseDatabase
import com.woodward.gainztrackerv2.database.entity.Category
import com.woodward.gainztrackerv2.repositories.CategoryRepository
import com.woodward.gainztrackerv2.repositories.ExerciseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CategoryViewModel @ViewModelInject constructor(val repository: CategoryRepository) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val listOfCategories : LiveData<List<Category?>> = repository.getCategoriesList()

    /**
     * Might need to place a mutableList of Live Data for the [listOfCategories] variable
     * because unsure as to whether the variable will receive updates via New Cat's, Updating and Deletion
     */

    private val _navigateToExerciseType= MutableLiveData<Int>()
    val navigateToExerciseType :LiveData<Int>
        get() = _navigateToExerciseType

    /**
     * Current id being set to identify the exercises to be chosen
     */
    fun onCategorySelected(id : Int) {
        _navigateToExerciseType.value = id
    }

    fun onCategoryToExerciseTypeNavigated() {
        _navigateToExerciseType.value = null
    }


    /**
     * Delete a category from a pre-existing list
     */
    suspend fun deleteCategory(category: Category) = viewModelScope.launch (Dispatchers.IO) {
        repository.deleteCategory(category)
    }

    /**
     * Retrieve all available categories from the local database
     */
    fun getAllCategories() : LiveData<List<Category?>> {
        return listOfCategories
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}