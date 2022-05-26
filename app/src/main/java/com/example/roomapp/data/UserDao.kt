package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllDataUser(): LiveData<List<User>>

    @Query("SELECT  email,password,isClient  FROM user_table where email =(:email) and password =(:password)  ")
    fun logIn(email: String , password: String): List<UserTuple>
}