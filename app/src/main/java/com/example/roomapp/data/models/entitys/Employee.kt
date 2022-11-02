package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val position:String,
    val experience:String,
    val isActivation:Int,
    val timeActivation:String,
    val user_ID:Long

    )



