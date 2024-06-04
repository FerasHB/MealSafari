package com.example.mealsafari.API

import com.example.mealsafari.ui.Data.CategoryList
import com.example.mealsafari.ui.Data.MealList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Die Basis-URL für die API
const val BASE_URL = "https://www.themealdb.com/api/json/v2/9973533/"

// Erstellen eines Moshi-Objekts mit einem Kotlin-Adapter für die JSON-Serialisierung und -Deserialisierung
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Erstellen eines Retrofit-Objekts mit Moshi als Konverter für JSON-Daten und der Basis-URL
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Definieren des ApiService-Interfaces für die Kommunikation mit der API.
 * Diese Schnittstelle enthält verschiedene Endpunkte zur Interaktion mit der TheMealDB API.
 */
interface ApiService {

    /**
     * Endpunkt für eine zufällige Mahlzeit.
     * @return Eine [MealList], die eine zufällige Mahlzeit enthält.
     */
    @GET("random.php")
    suspend fun getRandomMeal(): MealList

    /**
     * Endpunkt für beliebte Artikel in einer bestimmten Kategorie.
     * @param category Die Kategorie der beliebten Artikel.
     * @return Eine [MealList] mit den beliebten Artikeln in der angegebenen Kategorie.
     */
    @GET("filter.php?")
    suspend fun getPopularItem(@Query("c") category: String): MealList

    /**
     * Endpunkt für alle Mahlzeitenkategorien.
     * @return Eine [CategoryList], die alle Mahlzeitenkategorien enthält.
     */
    @GET("categories.php")
    suspend fun getAllMealCategories(): CategoryList

    /**
     * Endpunkt für Mahlzeiten in einer bestimmten Kategorie.
     * @param category Die Kategorie der Mahlzeiten.
     * @return Eine [MealList] mit den Mahlzeiten in der angegebenen Kategorie.
     */
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealList

    /**
     * Endpunkt für eine Mahlzeit anhand der ID.
     * @param id Die ID der Mahlzeit.
     * @return Eine [MealList] mit der Mahlzeit, die der angegebenen ID entspricht.
     */
    @GET("lookup.php?")
    fun getMealById(@Query("i") id: Long): MealList

    /**
     * Endpunkt für die Suche nach Mahlzeiten anhand eines Suchbegriffs.
     * @param searchQuery Der Suchbegriff.
     * @return Eine [MealList] mit den Mahlzeiten, die dem Suchbegriff entsprechen.
     */
    @GET("search.php?")
    suspend fun getBySearch(@Query("s") searchQuery: String): MealList
}

/**
 * Singleton-Objekt zur Bereitstellung des [ApiService].
 * Es wird ein Lazy-Initialisierer verwendet, um die Instanz des ApiService nur bei Bedarf zu erstellen.
 */
object MealApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
