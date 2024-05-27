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

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meal_information ORDER BY idMeal")
    fun getAllMeals(): LiveData<List<Meal>>

    @Query("SELECT * FROM meal_information WHERE idMeal = :id")
    fun getMealById(id: Long): LiveData<Meal>

    @Query("DELETE FROM meal_information WHERE idMeal = :id")
    suspend fun deleteMealById(id: Long)





    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    @Upsert()
    suspend fun saveNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)


    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>


}