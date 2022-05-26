package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addclient(client: ClientEntity)

    @Query("SELECT * FROM table_Client ORDER BY id ASC")
    fun readAllDataclient(): LiveData<List<ClientEntity>>


}