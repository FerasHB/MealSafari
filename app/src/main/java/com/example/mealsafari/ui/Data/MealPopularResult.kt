package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json
import syntax.com.playground.data.model.meal.Meal

class MealPopularResult (
    @Json(name= "meals")
    val mealList: List<MealPopular>

)
