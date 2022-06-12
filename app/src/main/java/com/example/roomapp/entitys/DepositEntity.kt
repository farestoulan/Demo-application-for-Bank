package com.example.roomapp.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_Deposit")
data class DepositEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val value_Deposit: String,
    val status_Request:String,
    val client_ID:Int
)
