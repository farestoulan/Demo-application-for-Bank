package com.example.roomapp.entitys

import android.widget.EditText
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_Withdraw")
class Withdraw(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val transactionID:String = UUID.randomUUID().toString(),
    val value_Withdraw: String,
    val status_Request:String,
    val  money_source :String,
    val request_Date: String,
    val approved_Date :String,
    val employee_Name:String,
    val client_ID:Int
    )

