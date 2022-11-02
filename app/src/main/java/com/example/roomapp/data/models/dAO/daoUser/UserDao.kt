package com.example.roomapp.data.models.dAO.daoUser

import androidx.room.*
import com.example.roomapp.data.models.entitys.User

@Dao
interface UserDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addUser(user: User):Long

    @Query("SELECT * FROM user_table ")
    fun readAllDataUser():List<User>


    @Query("SELECT  email,password,isClient,id  FROM user_table where email =(:email) and password =(:password)    ")
    fun logIn(email: String , password: String ): List<UserTuple>




    @Query("SELECT name  FROM user_table where  id =:id  ")
    fun returnEmployeeName(  id: Int):String


    @Query("SELECT id  FROM user_table where  id =:id  ")
    fun returnID1(  id: Int):Int

    @Query("SELECT id FROM user_table where user_table.id = (:id) ")
    fun returnID(id: Int): Int




}