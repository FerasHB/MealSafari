package com.example.mealsafari.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.room.MealDatabase
import com.example.mealsafari.ui.Data.Category
import com.example.mealsafari.ui.Data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import syntax.com.playground.data.model.meal.Meal

class MealRepository(private val apiService: MealApi, val dataBase: MealDatabase) {


    val getAllNotes= dataBase.dataDao.getAllNotes()

    private var _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal>
        get() = _randomMeal


    private var _mealPopular = MutableLiveData<List<Meal>>()
    val mealPopular: LiveData<List<Meal>>
        get() = _mealPopular


    private var _mealCategories = MutableLiveData<List<Meal>>()
    val mealCategories: LiveData<List<Meal>>
        get() = _mealCategories

    private var _mealByCategories = MutableLiveData<List<Category>>()
    val mealBYCategories: LiveData<List<Category>>
        get() = _mealByCategories


    private var _results = MutableLiveData<List<Meal>>()

    val results: LiveData<List<Meal>>
        get() = _results


    suspend fun saveNote(note: Note) {
        try {
            dataBase.dataDao.saveNote(note)
        } catch (e: Exception) {
            Log.e("TAG", "saveContact: ${e.message} ")
        }
    }


    suspend fun deleteNote(note: Long) {
        try {
        dataBase.dataDao.deleteNote(note)
        } catch (e: Exception) {
            Log.d("Repository", "Error in Database: $e")
        }
    }

    suspend fun updateNote(note: Note) {
        try {
            dataBase.dataDao.updateNote(note)

        }catch (e: Exception){
            Log.d("Repository", "Error in Database: $e")

        }

    }



    suspend fun delete(favoriteMeal: Meal) {
        dataBase.dataDao.deleteMeal(favoriteMeal)
    }


    suspend fun getAllMeals() {
        withContext(Dispatchers.IO) {
            val newMealsList = apiService.retrofitService.getRandomMeal().meals
            dataBase.dataDao.getAllMeals()
        }
    }




    suspend fun getResults(term: String) {
        try {
            val resultList = apiService.retrofitService.getBySearch(term)
            _results.value = resultList.meals
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }




    suspend fun getRandomMeal() {
        try {
            val result = apiService.retrofitService.getRandomMeal()
            _randomMeal.postValue(result.meals.random())
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getRandomMeal(): $e")
        }
    }


    suspend fun getMealPopular(popularMeal: String) {
        try {
            val result = apiService.retrofitService.getPopularItem(popularMeal)
            _mealPopular.postValue(result.meals)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getPopularMeal(): $e")
        }

    }

    suspend fun getAllMealCategories() {
        try {
            val result = apiService.retrofitService.getAllMealCategories()
            _mealByCategories.postValue(result.categories)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getAllMealCategories(): $e")
        }
    }

    suspend fun getMealsByCategory(category: String) {
        try {
            val result = apiService.retrofitService.getMealsByCategory(category)
            _mealCategories.postValue(result.meals)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getAllMealCategories(): $e")
        }
    }



    fun setMeal(meal: Meal) {

        _randomMeal.value = meal
    }

    suspend fun getMealById(mealId: String): Meal {
        return dataBase.dataDao.getMealById(mealId)
    }

    suspend fun deleteMealById(mealId: String) {
        dataBase.dataDao.deleteMealById(mealId)
    }

    suspend fun saveFavoriteMeal(meal: List<Meal>) {
        dataBase.dataDao.saveMeal(meal)
    }


    fun getMealByIdFromApi(id: String) {
        try {
            val result = apiService.retrofitService.getMealById(id)
            _randomMeal.postValue(result.meals[0])
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getAllMealCategories(): $e")
        }
    }

}