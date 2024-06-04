package com.example.mealsafari.room

import syntax.com.playground.data.model.meal.Meal
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mealsafari.ui.Data.Note

/**
 * Die Room-Datenbank für die Anwendung.
 * Diese Datenbank enthält die [Meal] und [Note] Tabellen.
 * Die Version der Datenbank ist 1 und das Schema wird nicht exportiert.
 */
@Database(entities = [Meal::class, Note::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
    /**
     * Die Data Access Object (DAO) für den Zugriff auf die Datenbank.
     */
    abstract val dataDao: DataDao
}

// Singleton-Instanz der Datenbank
private lateinit var INSTANCE: MealDatabase

/**
 * Funktion zum Abrufen der Datenbankinstanz.
 * Diese Funktion stellt sicher, dass nur eine Instanz der Datenbank erstellt wird (Singleton).
 *
 * @param context Der Anwendungskontext.
 * @return Die Singleton-Instanz der [MealDatabase].
 */
fun getDatabase(context: Context): MealDatabase {
    synchronized(MealDatabase::class.java) {
        // Überprüfen, ob die Datenbank bereits initialisiert ist
        if (!::INSTANCE.isInitialized) {
            // Wenn die Datenbank noch nicht existiert, dann erstelle sie
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                "meal_database"
            ).build()
        }
        // Rückgabe der Instanz der Datenbank
        return INSTANCE
    }
}
