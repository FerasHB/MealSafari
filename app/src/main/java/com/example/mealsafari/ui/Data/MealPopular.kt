package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json

class MealPopular(
    @Json(name = "strMeal")
    val mealName: String,
    @Json(name = "strMealThumb")
    val mealImage: String,
    @Json(name = "idMeal")
    val mealId: String
)