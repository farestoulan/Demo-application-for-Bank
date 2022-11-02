package com.example.roomapp.data.test_task_eng_khaled.users.employees

import android.content.Context
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.data.test_task_eng_khaled.users.Users

open class Employee(
    override val userName: String,
    override val firstName: String,
    override val lastName: String,
    override val age: Int,
    override val country: String,
    override val city: String,
    override val district: String
) : Users, Employees {
     override fun getMyRoles() {
         TODO("Not yet implemented")
     }

     override fun getAccountCreatedBy() {
         TODO("Not yet implemented")
     }

     override fun getMyTimeActivation() {
         TODO("Not yet implemented")
     }

     override fun getMyName(context: Context,id: Int): String? {
         return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).name
    }

    override fun getMyEmail(context: Context ,id: Int): String? {
        return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).email
    }

    override fun getMyPassword(context: Context,id: Int): String? {
        return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).password
    }


}