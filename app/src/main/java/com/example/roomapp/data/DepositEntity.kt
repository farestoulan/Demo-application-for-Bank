package com.example.roomapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.Relationships.ClientWithDeposits

@Entity(tableName = "add_Deposit")
data class DepositEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val balanceAmount :Int,
    val value_Deposit: String,
    val status_Request:String,
    val client_ID:Int
)
