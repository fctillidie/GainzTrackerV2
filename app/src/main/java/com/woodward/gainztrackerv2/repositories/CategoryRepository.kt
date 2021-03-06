package com.woodward.gainztrackerv2.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.woodward.gainztrackerv2.database.dao.CategoryDao
import com.woodward.gainztrackerv2.database.entity.Category
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(val dao: CategoryDao) {

    fun getCategoriesList() : LiveData<List<Category?>> {
        Timber.i("Get Categories Called")
        return dao.getAllCategories()
    }
    @WorkerThread
    suspend fun insertNewCategory(category: Category) {
        Timber.i("Insert New Category Called")
        dao.Insert(category)
    }

    @WorkerThread
    suspend fun deleteCategory(category: Category) {
        Timber.i("Delete Category Called")
        dao.Delete(category)
    }

    suspend fun checkIfNameExists(name : String?) : Boolean {
        Timber.i("Checking if name exists")
        return dao.checkIfCategoryExists(name)
    }
}