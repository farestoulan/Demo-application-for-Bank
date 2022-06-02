package com.example.roomapp.data

import androidx.navigation.findNavController
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.R

@Entity(tableName = "table_Client")
class ClientEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val creditType:String,
    val job : String,
    val user_ID:Long
  )


