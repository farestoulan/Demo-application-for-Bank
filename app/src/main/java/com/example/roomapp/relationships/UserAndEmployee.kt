package com.example.roomapp.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.entitys.Employee
import com.example.roomapp.entitys.User

  data class UserAndEmployee (
      @Embedded val user: User,
      @Relation(
        parentColumn = "id",
        entityColumn = "user_ID"
    )
    val employee: Employee
 )