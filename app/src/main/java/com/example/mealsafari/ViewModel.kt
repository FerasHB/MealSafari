package com.example.mealsafari

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope


import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import com.example.mealsafari.room.getDatabase
import com.example.mealsafari.ui.Data.Note
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import syntax.com.playground.data.model.meal.Meal

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealRepository(MealApi, getDatabase(application))

    val randomMeal = repository.randomMeal

    val popularMeal = repository.mealPopular

    val allMealCategories = repository.mealCategories

    val getMealsByCategory = repository.mealBYCategories

    val results = repository.results

    val getAllNotes = repository.getAllNotes


    val allMeals = repository.allMeals



    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>>
        get() = _favoriteMeals


    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText


    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }


    fun deleteNote(note: Long): Job {
        return viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)

        }
    }


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


    fun getMEalByIDFromApi(id: String) {
        viewModelScope.launch {
            repository.getMealByIdFromApi(id)
        }
    }

    fun updateInputText(text: String) {

        _inputText.value = text

    }




    fun addToFavorites(meal: Meal) {
        viewModelScope.launch {
            repository.insert(meal)
        }
    }

    fun removeFromFavorites(meal: Meal) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }

}

