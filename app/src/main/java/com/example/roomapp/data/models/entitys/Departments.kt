package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
class Departments (
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val department_Name:String,
    val branch_ID:Long

        )