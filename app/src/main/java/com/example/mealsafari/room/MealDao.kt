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

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meal_information ORDER BY idMeal")
     fun getAllMeals(): LiveData<List<Meal>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(itemDatas: List<Meal>)
}