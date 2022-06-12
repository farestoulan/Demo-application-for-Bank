package com.example.roomapp.entitys

import android.widget.EditText
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_Withdraw")
class Withdraw(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val value_Withdraw: String,
    val status_Request:String,
    val client_ID:Int
    )

