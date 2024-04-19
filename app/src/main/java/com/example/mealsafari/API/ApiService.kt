package com.example.mealsafari.API

import com.example.mealsafari.ui.Data.CategoryList
import com.example.mealsafari.ui.Data.MealList
import com.example.mealsafari.ui.Data.MealPopularResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.themealdb.com/api/json/v2/9973533/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService{
    @GET("random.php")
    suspend fun getRandomMeal(): MealList

    @GET("filter.php?")
    suspend fun getPopularItem(@Query("c")category: String):MealPopularResult

    @GET("categories.php")
    suspend fun getAllMealCategories(): CategoryList


}

object MealApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}