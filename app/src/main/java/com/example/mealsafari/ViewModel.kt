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
import kotlinx.coroutines.launch
import syntax.com.playground.data.model.meal.Meal

/**
 * ViewModel-Klasse für die MainActivity.
 * Diese Klasse stellt die Kommunikation zwischen der UI und dem Repository her.
 * Sie speichert und verwaltet Daten, die für verschiedene Fragmente der Anwendung benötigt werden.
 */
class ViewModel(application: Application) : AndroidViewModel(application) {

    // Initialisierung des MealRepository
    private val repository = MealRepository(MealApi, getDatabase(application))

    // LiveData für zufällige Mahlzeiten
    val meals = repository.randomMeal

    // LiveData für beliebte Mahlzeiten
    val popularMeal = repository.mealPopular

    // LiveData für alle Mahlzeitenkategorien
    val allMealCategories = repository.mealCategories

    // LiveData für Mahlzeiten nach Kategorie
    val getMealsByCategory = repository.mealBYCategories

    // LiveData für Suchergebnisse
    val results = repository.results

    // LiveData für alle Notizen
    val getAllNotes = repository.getAllNotes

    // LiveData für alle Mahlzeiten
    val allMeals = repository.allMeals


    val mealDetail = repository.mealDetails


    val favoriteMeal = repository.favoriteMeal

    // LiveData für bevorzugte Mahlzeiten
    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>>
        get() = _favoriteMeals

    // LiveData für eingegebenen Suchtext
    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String>
        get() = _inputText

    /**
     * Methode zum Speichern einer Notiz.
     * @param note Die zu speichernde Notiz.
     */
    fun saveNote(note: Note) {
        viewModelScope.launch {
            repository.saveNote(note)
        }
    }

    // Methode zum Löschen einer Notiz
    fun deleteNote(note: Long) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    // Methode zum Aktualisieren einer Notiz
    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    /**
     * Methode zum Laden von Daten basierend auf dem Suchbegriff.
     * @param term Der Suchbegriff, nach dem gesucht wird.
     */
    fun loadData(term: String) {
        viewModelScope.launch {
            repository.getResults(term)
        }
    }

    /**
     * Methode zum Setzen einer ausgewählten Mahlzeit.
     * @param meal Die ausgewählte Mahlzeit.
     */
    fun setMeal(meal: Meal) {
        repository.setMeal(meal)
    }


    // Methode zum Laden einer zufälligen Mahlzeit
    fun loadRandomMeal() {
        viewModelScope.launch {
            repository.getRandomMeal()
        }
    }

    // Methode zum Laden beliebter Mahlzeiten
    fun loadPopularMeal(category: String) {
        viewModelScope.launch {
            repository.getMealPopular(category)
        }
    }

    // Methode zum Laden aller Mahlzeitenkategorien
    fun loadAllMealCategories() {
        viewModelScope.launch {
            repository.getAllMealCategories()
        }
    }

    /**
     * Methode zum Laden von Mahlzeiten basierend auf der Kategorie.
     * @param category Die Kategorie, nach der gesucht wird.
     */
    fun loadMealByCategory(category: String) {
        viewModelScope.launch {
            repository.getMealsByCategory(category)
        }
    }

    /**
     * Methode zum Aktualisieren des eingegebenen Suchtexts.
     * @param text Der eingegebene Suchtext.
     */
    fun updateInputText(text: String) {
        _inputText.value = text
    }

    // Methode zum Hinzufügen einer Mahlzeit zu den Favoriten
    fun addToFavorites(meal: Meal) {
        viewModelScope.launch {
            repository.insert(meal)
        }
    }

    // Methode zum Entfernen einer Mahlzeit aus den Favoriten
    fun removeFromFavorites(meal: Meal) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }

    // Methode zum Löschen einer Mahlzeit anhand der ID
    fun deleteMealById(mealId: Long) {
        viewModelScope.launch {
            repository.deleteMealById(mealId)
        }
    }



    /**
     * Methode zum Abrufen von Mahlzeiten anhand der ID.
     * @param mealId Die ID der abzurufenden Mahlzeit.
     */
    fun getMealById(mealId: String) {
        viewModelScope.launch {
            repository.getMealById(mealId)
            }
        }
    }

