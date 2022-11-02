package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "branches")
class Branches (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val branch_Name:String
        )