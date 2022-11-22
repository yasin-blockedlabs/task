package com.example.task.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "got_character")
data class GotCharacter(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val title: String,
    val family: String,
    val image: String,
    val imageUrl: String
)