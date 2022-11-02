package com.example.roomapp.data.data_source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.data.models.dAO.*
import com.example.roomapp.data.models.dAO.daoUser.UserDao
import com.example.roomapp.data.models.entitys.*

@Database(
    entities = [User::class, ClientEntity::class, Employee::class, DepositEntity::class,
        Withdraw::class, Departments::class, Branches::class, Roles::class, UsersRules::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun clientDao(): ClientDao
    abstract fun depositDao(): DepositDao
    abstract fun withdrawDao(): WithdrawDAO
    abstract fun branchDao():BranchesDAO
    abstract fun departmentDao():DepartmentDAO
    abstract fun rulesDao():RulesDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}