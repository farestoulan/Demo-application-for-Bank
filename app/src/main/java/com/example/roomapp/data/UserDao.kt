package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.Relationships.UserAndClient
import com.example.roomapp.Relationships.UserAndEmployee

@Dao
interface UserDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addUser(user: User):Long

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllDataUser(): LiveData<List<User>>

    @Query("SELECT  email,password,isClient,id  FROM user_table where email =(:email) and password =(:password)  ")
    fun logIn(email: String , password: String): List<UserTuple>

//    @Transaction
//    @Query("SELECT * FROM user_table")
//    fun getUserAndClient(): List<UserAndClient>

    @Transaction
    @Query("SELECT * FROM  user_table")
    fun getUserAndEmployee(): List<UserAndEmployee>




}