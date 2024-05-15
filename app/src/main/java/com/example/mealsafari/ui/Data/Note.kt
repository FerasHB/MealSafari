package com.example.mealsafari.ui.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "notes")

data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var noteTitle: String,
    var noteDesc: String


) : Serializable
