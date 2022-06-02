package com.example.roomapp.data

import androidx.room.ColumnInfo

data class UserTuple(
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "isClient") val isClient: Boolean?,
    @ColumnInfo(name = "id") val id: Int


)