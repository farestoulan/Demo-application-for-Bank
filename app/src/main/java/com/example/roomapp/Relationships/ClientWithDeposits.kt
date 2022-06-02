package com.example.roomapp.Relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.roomapp.data.ClientEntity
import com.example.roomapp.data.DepositEntity

data class ClientWithDeposits(
    @Embedded val client: ClientEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "client_ID"
    )
    val deposit: List<DepositEntity>
)
