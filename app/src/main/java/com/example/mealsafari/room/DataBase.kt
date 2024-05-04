package com.example.mealsafari.room

import syntax.com.playground.data.model.meal.Meal
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Meal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {
    abstract val mealDao: MealDao
}

private lateinit var INSTANCE: MealDatabase


//Diese Variable enthält die eine konkrete Instanz unserer Datenbank

fun getDatabase(context: Context): MealDatabase {

    synchronized(MealDatabase::class.java) {

        //Überprüfe ob die Datenbank bereits existiert
        if (!::INSTANCE.isInitialized) {

            //Wenn die Datenbank noch nicht existiert dann erstelle sie
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                "meal_database"
            ).build()
        }

    return INSTANCE
    }
    //In jedem Fall liefer die Instanz der Datenbank zurück

}