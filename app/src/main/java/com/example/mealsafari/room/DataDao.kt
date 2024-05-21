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

    @Query("SELECT * FROM meal_information WHERE idMeal =:id")
    fun getMealById(id: String): Meal

    @Query("DELETE FROM meal_information WHERE idMeal =:id")
    fun deleteMealById(id:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(itemData: List<Meal>)






    @Upsert()
    suspend fun saveNote(note: Note)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)


    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<Note>>

}