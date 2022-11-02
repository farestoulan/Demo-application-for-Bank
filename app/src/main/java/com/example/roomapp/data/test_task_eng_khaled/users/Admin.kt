package com.example.roomapp.data.test_task_eng_khaled.users

import android.content.Context
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.data.test_task_eng_khaled.users.employees.TeamListInterface

class Admin(
    override val userName: String,
    override val firstName: String,
    override val lastName: String,
    override val age: Int,
    override val country: String,
    override val city: String,
    override val district: String
) :Users,TeamListInterface {

    override fun getMyName(context: Context,id:Int): String {
        return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).name
    }

    override fun getMyEmail(context: Context,id: Int): String {
        return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).email
    }

    override fun getMyPassword(context: Context,id: Int): String {
        return UserDatabase.getDatabase(context).userDao().readAllDataUser().get(id).password
    }

    override fun getMyTeamList() {
        TODO("Not yet implemented")
    }

    override fun submitPollRequest() {
        TODO("Not yet implemented")
    }

    override fun rejectPollRequest() {
        TODO("Not yet implemented")
    }





}