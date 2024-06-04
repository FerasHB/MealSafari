package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json
import syntax.com.playground.data.model.meal.Meal

/**
 * Datenklasse, die eine Liste von Mahlzeiten repräsentiert.
 * @param meals Eine Liste von Mahlzeiten.
 */
data class MealList(
    @Json(name= "meals")
    val meals: List<Meal>
)
