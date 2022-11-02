package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
 data class User(
    @PrimaryKey(autoGenerate = true)

    val id: Long,
    val name: String,

    val email: String,
    val password: String,

    val isClient: Int

    )

