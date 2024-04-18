package com.example.mealsafari

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import kotlinx.coroutines.launch

class MealViewModel:ViewModel() {

    private val repository = MealRepository(MealApi)

    val randomMeal = repository.randomMeal

    val PopularMeal = repository.mealPopular



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
}