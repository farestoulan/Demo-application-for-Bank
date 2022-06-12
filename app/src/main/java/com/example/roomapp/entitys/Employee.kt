package com.example.roomapp.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val position:String,
    val experience:String,
    val user_ID:Long

    )



