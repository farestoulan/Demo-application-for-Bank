package com.example.roomapp.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.entitys.ClientEntity
import com.example.roomapp.entitys.User

 data class UserAndClient  (
     @Embedded val user: User,
     @Relation(
        parentColumn = "id",
        entityColumn = "user_ID"
    )
    val client: ClientEntity
)