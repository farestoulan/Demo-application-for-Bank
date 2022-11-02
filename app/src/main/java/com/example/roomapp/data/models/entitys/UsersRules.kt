package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_Rules")
class UsersRules (
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val user_ID :Int,
    val rules_ID:Int
        )
