package com.example.roomapp.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "add_Deposit")
data class DepositEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val transactionID:String = UUID.randomUUID().toString(),
    val value_Deposit: String,
    val status_Request:String,
    val money_source :String,
    val request_Date: String,
    val approved_Date :String,
    val employee_name:String,
    val client_ID:Int,
    val employee_ID:Int
)
