package com.example.roomapp.data.models.dAO.daoUser

import androidx.room.ColumnInfo

data class UserTuple(
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "isClient") val isClient: Int?,
    @ColumnInfo(name = "id") val id: Int


)