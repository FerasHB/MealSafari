package com.example.mealsafari

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import com.example.mealsafari.room.getDatabase
import com.example.mealsafari.ui.Data.MealDetail
import kotlinx.coroutines.launch
import syntax.com.playground.data.model.meal.Meal

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealRepository(MealApi, getDatabase(application))

    val randomMeal = repository.randomMeal

    val PopularMeal = repository.mealPopular

    val allMealCategories = repository.mealCategories

    val getMealsByCategory = repository.mealBYCategories

    val getMealById = repository.mealDetail

    private val mutableMealBottomSheet = MutableLiveData<List<MealDetail>>()

    val selectedMeal = repository.selectedMeal

    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>>
        get() = _favoriteMeals

    fun getAllMeals(){
        viewModelScope.launch {
            repository.getAllMeals()
        }
    }


        fun addToFavorites(meal: Meal) {
            val currentList = _favoriteMeals.value.orEmpty().toMutableList()
            currentList.add(meal)
            _favoriteMeals.value = currentList

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


    fun getMealById(id: String) {
        viewModelScope.launch {
            repository.getMealById(id)
        }
    }

    fun observeMealBottomSheet(): LiveData<List<MealDetail>> {
        return mutableMealBottomSheet
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
}