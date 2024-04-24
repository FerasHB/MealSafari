package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json
import syntax.com.playground.data.model.meal.Meal

data class MealList(
    @Json(name= "meals")
    val meals: List<Meal>
)