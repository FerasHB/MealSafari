package com.example.mealsafari.ui.Data

import com.squareup.moshi.Json

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