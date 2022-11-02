package com.example.roomapp.data.models.dAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.data.models.entitys.UsersRules

@Dao
interface UsersRulesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addUsersRules(usersRules: UsersRules)

    @Query("SELECT * FROM users_Rules ORDER BY id ASC")
    fun readAllUsersRules(): LiveData<List<UsersRules>>
}