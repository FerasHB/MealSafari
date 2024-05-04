package com.example.mealsafari.repo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.room.MealDatabase
import com.example.mealsafari.ui.Data.Category
import com.example.mealsafari.ui.Data.MealDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import syntax.com.playground.data.model.meal.Meal

class MealRepository(private val apiService: MealApi, val dataBase: MealDatabase) {
    val getAllMeals: LiveData<List<Meal>> = dataBase.mealDao.getAllMeals()

   suspend fun getAllMeals(){
        withContext(Dispatchers.IO){
            val newMealsList = apiService.retrofitService.getRandomMeal().meals
            dataBase.mealDao.getAllMeals()
        }
    }
    

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

    private var _mealDetail = MutableLiveData<List<MealDetail>>()
    val mealDetail: LiveData<List<MealDetail>>
        get() = _mealDetail

    private var _selectedMeal = MutableLiveData<Meal>()

    val selectedMeal: LiveData<Meal>
        get() = _selectedMeal


    fun selectedMeal(meal: Meal) {
        _selectedMeal.value = meal
    }


    suspend fun upsertMeal(meal: Meal) {
        dataBase.mealDao.upsertMeal(meal)

    }

    suspend fun deleteMeal(meal: Meal) {
        dataBase.mealDao.deleteMeal(meal)
    }

    suspend fun getRandomMeal() {
        try {
            val result = apiService.retrofitService.getRandomMeal()
            _randomMeal.postValue(result.meals.first())
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getRandomMeal(): $e")
        }
    }


    suspend fun getMealPopular(category: String) {
        try {
            val result = apiService.retrofitService.getPopularItem(category)
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

    fun getMealById(id: String) {
        try {
            val result = apiService.retrofitService.getMealById(id)
            _mealDetail.postValue(result.meals)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API getAllMealCategories(): $e")
        }
    }

}