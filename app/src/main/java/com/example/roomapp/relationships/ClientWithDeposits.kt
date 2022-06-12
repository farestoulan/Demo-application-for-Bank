package com.example.roomapp.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.entitys.ClientEntity
import com.example.roomapp.entitys.DepositEntity

data class ClientWithDeposits(
    @Embedded val client: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "client_ID"
    )
    val deposit: List<DepositEntity>
)
