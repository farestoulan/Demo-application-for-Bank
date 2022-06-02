package com.example.roomapp.Relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.data.ClientEntity
import com.example.roomapp.data.Employee
import com.example.roomapp.data.User

 data class UserAndClient  (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_ID"
    )
    val client: ClientEntity
)