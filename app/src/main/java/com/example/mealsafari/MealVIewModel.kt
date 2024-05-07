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


    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>>
        get() = _favoriteMeals


    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    val results = repository.results


    fun loadData(term: String) {
        viewModelScope.launch {
            repository.getResults(term)
        }
    }


    fun setMeal(meal: Meal) {
        repository.setMeal(meal)
    }


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
    fun updateInputText(text: String) {
        _inputText.value = text
    }

}