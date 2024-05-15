package com.example.mealsafari.API

import com.example.mealsafari.ui.Data.CategoryList
import com.example.mealsafari.ui.Data.MealList
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


interface ApiService {
    @GET("random.php")
    suspend fun getRandomMeal(): MealList

    @GET("filter.php?")
    suspend fun getPopularItem(@Query("c") category: String): MealList


    @GET("categories.php")
    suspend fun getAllMealCategories(): CategoryList

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealList

    @GET("lookup.php?")
    fun getMealById(@Query("i") id: String): MealList

    @GET("search.php?")
    suspend fun getBySearch(@Query("s") searchQuery: String): MealList


}

object MealApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}