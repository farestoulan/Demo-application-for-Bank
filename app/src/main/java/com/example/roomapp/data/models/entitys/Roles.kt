package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rules")
class Roles(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val rules_Names:String,
    val department_ID:Long
)