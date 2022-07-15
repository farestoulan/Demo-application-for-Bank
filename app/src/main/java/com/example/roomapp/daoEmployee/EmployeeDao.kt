package com.example.roomapp.daoEmployee

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.entitys.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addemployee(employee: Employee)

    @Query("SELECT * FROM employees ORDER BY id ASC")
    fun readAllDataemployee(): LiveData<List<Employee>>




    @Query("SELECT id FROM employees where employees.user_ID = (:userFroginid) ")
    fun returnEmployeeID(userFroginid: Int): Int

}