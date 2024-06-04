package com.example.mealsafari.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mealsafari.API.MealApi
import com.example.mealsafari.room.MealDatabase
import com.example.mealsafari.ui.Data.Category
import com.example.mealsafari.ui.Data.Note
import com.example.mealsafari.ui.Data.PopularMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.withContext
import syntax.com.playground.data.model.meal.Meal

/**
 * Repository-Klasse für den Zugriff auf die API und die lokale Datenbank.
 * @param apiService Instanz von [MealApi] für den API-Zugriff.
 * @param dataBase Instanz von [MealDatabase] für den Datenbankzugriff.
 */
class MealRepository(private val apiService: MealApi, val dataBase: MealDatabase) {

    // LiveData für alle Notizen aus der Datenbank
    val getAllNotes = dataBase.dataDao.getAllNotes()

    // LiveData für alle Mahlzeiten aus der Datenbank
    val allMeals: LiveData<List<Meal>> = dataBase.dataDao.getAllMeals()

    // MutableLiveData und LiveData für eine zufällige Mahlzeit
    private var _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal>
        get() = _randomMeal

    // MutableLiveData und LiveData für beliebte Mahlzeiten
    private var _mealPopular = MutableLiveData<List<PopularMeal>>()
    val mealPopular: LiveData<List<PopularMeal>>
        get() = _mealPopular

    // MutableLiveData und LiveData für Mahlzeiten nach Kategorien
    private var _mealCategories = MutableLiveData<List<Meal>>()
    val mealCategories: LiveData<List<Meal>>
        get() = _mealCategories

    // MutableLiveData und LiveData für Mahlzeitenkategorien
    private var _mealByCategories = MutableLiveData<List<Category>>()
    val mealBYCategories: LiveData<List<Category>>
        get() = _mealByCategories

    // MutableLiveData und LiveData für Suchergebnisse
    private var _results = MutableLiveData<List<Meal>>()
    val results: LiveData<List<Meal>>
        get() = _results

    // LiveData für Mahlzeitendetails
    private val _mealDetails = MutableLiveData<Meal>()
    val mealDetails: LiveData<Meal> = _mealDetails


    private val _favoriteMeal = MutableLiveData<List<Meal>>()
    val favoriteMeal = _favoriteMeal

    /**
     * Speichert eine Notiz in der Datenbank.
     * @param note Die zu speichernde Notiz.
     */
    suspend fun saveNote(note: Note) {
        try {
            dataBase.dataDao.saveNote(note)
        } catch (e: Exception) {
            Log.e(TAG, "saveNote: ${e.message}")
        }
    }

    /**
     * Löscht eine Notiz aus der Datenbank.
     * @param noteId Die ID der zu löschenden Notiz.
     */
    suspend fun deleteNote(noteId: Long) {
        try {
            dataBase.dataDao.deleteNote(noteId)
        } catch (e: Exception) {
            Log.e(TAG, "deleteNote: Error in Database: $e")
        }
    }

    /**
     * Aktualisiert eine Notiz in der Datenbank.
     * @param note Die zu aktualisierende Notiz.
     */
    suspend fun updateNote(note: Note) {
        try {
            dataBase.dataDao.updateNote(note)
        } catch (e: Exception) {
            Log.e(TAG, "updateNote: Error in Database: $e")
        }
    }

    /**
     * Ruft Suchergebnisse von der API ab.
     * @param term Der Suchbegriff.
     */
    suspend fun getResults(term: String) {
        try {
            val resultList = apiService.retrofitService.getBySearch(term)
            _results.value = resultList.meals
        } catch (e: Exception) {
            Log.e(TAG, "getResults: Error loading Data from API: $e")
        }
    }

    /**
     * Ruft eine zufällige Mahlzeit von der API ab.
     */
    suspend fun getRandomMeal() {
        try {
            val result = apiService.retrofitService.getRandomMeal()
            _randomMeal.postValue(result.meals.random())
        } catch (e: Exception) {
            Log.e(TAG, "getRandomMeal: Error loading Data from API: $e")
        }
    }

    /**
     * Ruft beliebte Mahlzeiten von der API ab.
     * @param popularMeal Die Kategorie der beliebten Mahlzeit.
     */
    suspend fun getMealPopular(popularMeal: String) {
        try {
            val result = apiService.retrofitService.getPopularItem(popularMeal)
            _mealPopular.postValue(result.meals)
        } catch (e: Exception) {
            Log.e(TAG, "getMealPopular: Error loading Data from API: $e")
        }
    }

    /**
     * Ruft alle Mahlzeitenkategorien von der API ab.
     */
    suspend fun getAllMealCategories() {
        try {
            val result = apiService.retrofitService.getAllMealCategories()
            _mealByCategories.postValue(result.categories)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMealCategories: Error loading Data from API: $e")
        }
    }

    /**
     * Ruft Mahlzeiten nach Kategorie von der API ab.
     * @param category Die Kategorie der Mahlzeiten.
     */
    suspend fun getMealsByCategory(category: String) {
        try {
            val result = apiService.retrofitService.getMealsByCategory(category)
            _mealCategories.postValue(result.meals)
        } catch (e: Exception) {
            Log.e(TAG, "getMealsByCategory: Error loading Data from API: $e")
        }
    }

    /**
     * Ruft eine Mahlzeit nach ID von der API ab.
     * @param mealId Die ID der Mahlzeit.
     * @return Die gefundene Mahlzeit oder null.
     */
    suspend fun getMealById(mealId: String) {
        try {
            val result = apiService.retrofitService.getMealById(mealId)
            _mealDetails.postValue(result.meals.firstOrNull())
        } catch (e: Exception) {
            Log.e(TAG, "getMealById: Error loading Data from API: $e")

        }
    }

    /**
     * Löscht eine Mahlzeit nach ID aus der Datenbank.
     * @param mealId Die ID der zu löschenden Mahlzeit.
     */
    fun deleteMealById(mealId: Long) {
        dataBase.dataDao.deleteMealById(mealId)
    }

    /**
     * Setzt eine Mahlzeit im MutableLiveData.
     * @param meal Die zu setzende Mahlzeit.
     */
    fun setMeal(meal: Meal) {
        _randomMeal.value = meal
    }


    /**
     * Fügt eine Mahlzeit in die Datenbank ein.
     * @param meal Die einzufügende Mahlzeit.
     */
    suspend fun insert(meal: Meal) {
        dataBase.dataDao.insertMeal(meal)
    }

    /**
     * Löscht eine Mahlzeit aus der Datenbank.
     * @param meal Die zu löschende Mahlzeit.
     */
    suspend fun deleteMeal(meal: Meal) {
        dataBase.dataDao.deleteMeal(meal)
    }
}
