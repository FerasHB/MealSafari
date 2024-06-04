package com.example.mealsafari.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.mealsafari.ui.Data.Note
import syntax.com.playground.data.model.meal.Meal

/**
 * Data Access Object (DAO) für den Zugriff auf die Datenbanktabellen.
 * Hier sind die Funktionen definiert, um auf die Mahlzeiten- und Notizdaten zuzugreifen.
 */
@Dao
interface DataDao {
    // Einfügen einer Mahlzeit in die Datenbank
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    // Abrufen aller Mahlzeiten aus der Datenbank
    @Query("SELECT * FROM meal_information")
    fun getAllMeals(): LiveData<List<Meal>>

    // Löschen einer Mahlzeit aus der Datenbank
    @Delete
    suspend fun deleteMeal(meal: Meal)

    // Abrufen einer Mahlzeit anhand der ID
    @Query("SELECT * FROM meal_information WHERE idMeal = :id")
    fun getMealById(id: Long): Meal

    // Löschen einer Mahlzeit aus der Datenbank anhand der ID
    @Query("DELETE FROM meal_information WHERE idMeal = :id")
    fun deleteMealById(id: Long)





    // Einfügen einer Notiz in die Datenbank
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // Speichern einer Notiz in der Datenbank
    @Upsert
    suspend fun saveNote(note: Note)

    // Aktualisieren einer Notiz in der Datenbank
    @Update
    suspend fun updateNote(note: Note)

    // Löschen einer Notiz aus der Datenbank anhand der ID
    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

    // Abrufen aller Notizen aus der Datenbank
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
