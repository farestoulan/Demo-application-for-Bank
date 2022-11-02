package com.example.roomapp.data.models.dAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.data.models.entitys.Roles

@Dao
interface RulesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addRules(roles: Roles)

    @Query("SELECT * FROM rules ORDER BY id ASC")
    fun readAllRules(): LiveData<List<Roles>>
}