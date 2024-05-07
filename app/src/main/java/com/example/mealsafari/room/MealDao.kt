package com.example.mealsafari.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import syntax.com.playground.data.model.meal.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)


    @Query("SELECT * FROM meal_information")
    fun getAllFavoriteMeals(): LiveData<List<Meal>>

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeal(favoriteMeal: Meal)

    @Query("SELECT * FROM meal_information ORDER BY idMeal")
    suspend fun getAllMeals(): LiveData<List<Meal>>

    @Query("SELECT * FROM meal_information WHERE idMeal =:id")
    suspend fun getMealById(id: String): Meal

    @Query("DELETE FROM meal_information WHERE idMeal =:id")
    suspend fun deleteMealById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(itemDatas: List<Meal>)
}