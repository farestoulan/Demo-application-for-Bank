package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DepositDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeposit(depositEntity: DepositEntity)

    @Query("SELECT * FROM add_Deposit ORDER BY id ASC")
    fun readAllDeposit(): LiveData<List<DepositEntity>>
}