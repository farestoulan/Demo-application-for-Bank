package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.Relationships.ClientWithDeposits

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addclient(client: ClientEntity)

    @Query("SELECT * FROM table_Client ORDER BY id ASC")
    fun readAllDataclient(): LiveData<List<ClientEntity>>

//    @Transaction
//    @Query("SELECT * FROM table_Client inner join add_Deposit where table_Client.id=add_Deposit.client_ID")
//    fun getClientWithDeposits(): List<ClientWithDeposits>

    @Transaction
    @Query("SELECT * FROM table_Client inner join add_Deposit  where table_Client.id=add_Deposit.client_ID  ")
    fun getClientWithDeposits(): List<ClientWithDeposits>
}