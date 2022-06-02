package com.example.roomapp.Relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.data.Employee
import com.example.roomapp.data.User

  data class UserAndEmployee (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_ID"
    )
    val employee: Employee
 )