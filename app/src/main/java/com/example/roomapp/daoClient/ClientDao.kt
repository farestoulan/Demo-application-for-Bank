package com.example.roomapp.daoClient

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.entitys.ClientEntity

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addclient(client: ClientEntity)

    @Query("SELECT * FROM table_Client ORDER BY id ASC")
    fun readAllDataclient(): LiveData<List<ClientEntity>>

    @Query("SELECT id FROM table_Client where table_Client.user_ID = (:userFroginid) ")
    fun returnClientID(userFroginid: Int): Int

    @Query("UPDATE table_Client SET amount_Balance=:amount_Balance  WHERE id = :id")
    fun updateBalance(amount_Balance: Int, id: Int)

    @Query("SELECT   amount_Balance from table_Client where id=:id ")
    fun returnBalance(id: Int): Int

    @Query("UPDATE   table_Client SET pending_Balance = :pendingBalance where id=:id ")
    fun updatePendingBalance(pendingBalance:Int,   id: Int): Int

    @Query("SELECT   pending_Balance from table_Client where id=:id ")
    fun returnPendingBalance(id: Int): Int
}