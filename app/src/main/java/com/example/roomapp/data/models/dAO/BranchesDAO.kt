package com.example.roomapp.data.models.dAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.data.models.entitys.Branches

@Dao
interface BranchesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addBranches(branches: Branches):Long

    @Query("SELECT * FROM branches ORDER BY id ASC")
    fun readAllBranches(): LiveData<List<Branches>>
}