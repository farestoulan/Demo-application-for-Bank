package com.example.roomapp.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val name: String,
    val email: String,
    val password: Int,
    val isClient: Boolean
)