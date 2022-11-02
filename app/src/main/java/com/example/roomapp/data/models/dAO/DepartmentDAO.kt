package com.example.roomapp.data.models.dAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.data.models.entitys.Departments

@Dao
interface DepartmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addDepartments(departments: Departments):Long

    @Query("SELECT * FROM departments ORDER BY id ASC")
    fun readAllDepartments(): LiveData<List<Departments>>
}