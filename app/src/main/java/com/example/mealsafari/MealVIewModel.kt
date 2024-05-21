package com.example.mealsafari

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope


import com.example.mealsafari.API.MealApi
import com.example.mealsafari.repo.MealRepository
import com.example.mealsafari.room.getDatabase
import com.example.mealsafari.ui.Data.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import syntax.com.playground.data.model.meal.Meal

class MealViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealRepository(MealApi, getDatabase(application))

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

    val setNote =repository.setNote

    val getAllNotes = repository.getAllNotes


    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }
    fun addNote(note: Note): Job {
        return viewModelScope.launch {
            repository.insertNote(note)
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


    fun searchNotes(query: String?) {
        repository.searchNotes(query)
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


    fun deleteMealById(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMealById(mealId)
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

}