package com.example.roomapp.data.models.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_Client")
class ClientEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount_Balance :Int,
    val pending_Balance :String,
    val creditType:String ,
    val job : String,
    val user_ID:Long
  )


