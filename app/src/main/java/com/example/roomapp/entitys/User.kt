package com.example.roomapp.entitys

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
 data class User (
    @PrimaryKey(autoGenerate = true)

    val id: Long=0 ,
    val name: String,

    val email: String,
    val password: String,

    val isClient: Boolean,

)

