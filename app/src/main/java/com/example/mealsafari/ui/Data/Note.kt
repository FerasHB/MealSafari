package com.example.mealsafari.ui.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Datenklasse, die eine Notiz repräsentiert.
 * @param id Die eindeutige ID der Notiz.
 * @param noteTitle Der Titel der Notiz.
 * @param noteDesc Die Beschreibung oder der Inhalt der Notiz.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var noteTitle: String,
    var noteDesc: String
) : Serializable
