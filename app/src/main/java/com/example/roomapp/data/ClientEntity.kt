package com.example.roomapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_Client")
class ClientEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val creditType:String,
    val job : String
  )
