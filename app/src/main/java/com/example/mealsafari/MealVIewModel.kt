package com.example.mealsafari

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import kotlinx.coroutines.launch
import syntax.com.playground.data.model.meal.Meal

class MealViewModel:ViewModel() {

    private val repository = MealRepository(MealApi)

    val randomMeal = repository.randomMeal

    val PopularMeal = repository.mealPopular

    val allMealCategories = repository.mealCategories

    val getMealsByCategory = repository.mealBYCategories



    fun setMeal(meal: Meal){
        repository.setMeal(meal)
    }
    fun loadRandomMeal() {
        viewModelScope.launch {
            repository.getRandomMeal()
        }
    }

    fun loadPopularMeal(category :String){
        viewModelScope.launch {
            repository.getMealPopular(category)
        }
    }


    fun loadAllMealCategories() {
        viewModelScope.launch {
            repository.getAllMealCategories()
        }
    }

    fun loadMealByCategory(category: String){
        viewModelScope.launch {
            repository.getMealsByCategory(category)
        }
    }
}