package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json

/**
 * Datenklasse, die eine Kategorie von Mahlzeiten repräsentiert.
 * @param categoryName Der Name der Kategorie.
 * @param categoryId Die ID der Kategorie.
 * @param categoryImage Die URL des Bildes der Kategorie.
 * @param categoryDescription Die Beschreibung der Kategorie.
 */
class Category(
    @Json(name = "strCategory")
    val categoryName: String,
    @Json(name = "idCategory")
    val categoryId: String,
    @Json(name = "strCategoryThumb")
    val categoryImage: String,
    @Json(name = "strCategoryDescription")
    val categoryDescription: String,
)
