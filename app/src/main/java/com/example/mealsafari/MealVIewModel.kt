package com.example.mealsafari

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealsafari.API.ApiService
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import com.example.mealsafari.room.getDatabase
import com.example.mealsafari.ui.Data.MealDetail
import com.example.mealsafari.ui.Data.MealList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import syntax.com.playground.data.model.meal.Meal

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealRepository(MealApi, getDatabase(application))
    private val mutableMealDetail = MutableLiveData<List<MealDetail>>()
    val randomMeal = repository.randomMeal

    val popularMeal = repository.mealPopular

    val allMealCategories = repository.mealCategories

    val getMealsByCategory = repository.mealBYCategories

    val getMealById = repository.mealDetail

    private val mutableMealBottomSheet = MutableLiveData<List<Meal>>()

    val selectedMeal = repository.selectedMeal

    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>>
        get() = _favoriteMeals

    private val _selectedMeal1 = MutableLiveData<Meal>()
    val selectedMeal1: LiveData<Meal>
        get() = _selectedMeal1


  /*  fun setSelectedMeal(meal: Meal) {
        _selectedMeal1.value = meal
    }

    fun addToFavorites() {
        val mealToAdd = _selectedMeal1.value
        if (mealToAdd != null) {
            // Füge mealToAdd zu den Favoriten hinzu
        }
    }*/



    fun getAllMeals(){
        viewModelScope.launch {
            repository.getAllMeals()
        }
    }


    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            repository.upsertMeal(meal)
        }
    }
    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }


   /* fun getMealById(id: String) {
        viewModelScope.launch {
            repository.getMealById(id)
        }
    }*/

    fun observeMealBottomSheet(): MutableLiveData<List<Meal>> {
        return mutableMealBottomSheet
    }


    fun setMeal(meal: Meal) {
        repository.setMeal(meal)
    }

    /*fun getMealById(id:String){
        viewModelScope.launch {
            repository.getMealById(id)
        }
    }*/
    fun loadRandomMeal() {
        viewModelScope.launch {
            repository.getRandomMeal()
        }
    }

    fun loadPopularMeal(category: String) {
        viewModelScope.launch {
            repository.getMealPopular(category)
        }
    }


    fun loadAllMealCategories() {
        viewModelScope.launch {
            repository.getAllMealCategories()
        }
    }

    fun loadMealByCategory(category: String) {
        viewModelScope.launch {
            repository.getMealsByCategory(category)
        }
    }

    fun isMealSavedInDatabase(mealId: String): Boolean {
        var meal: Meal? = null
        runBlocking(Dispatchers.IO) {
            meal = repository.getMealById(mealId)
        }
        if (meal == null)
            return false
        return true

    }

    fun deleteMealById(mealId:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMealById(mealId)
        }
    }

    fun getMEalByIDFromApi(id:String){
        viewModelScope.launch {
            repository.getMealByIdFromApi(id)
        }
    }

}