package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addemployee(employee: Employee)

    @Query("SELECT * FROM employees ORDER BY id ASC")
    fun readAllDataemployee(): LiveData<List<Employee>>


}