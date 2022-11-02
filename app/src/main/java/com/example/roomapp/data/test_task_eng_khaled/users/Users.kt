package com.example.roomapp.data.test_task_eng_khaled.users

import android.content.Context

interface Users {
    val userName: String
    val firstName: String
    val lastName: String
    val age: Int
    val country: String
    val city: String
    val district: String



    fun getMyName(context: Context, id: Int): String?
    fun getMyEmail(context: Context, id: Int): String?
    fun getMyPassword(context: Context, id: Int): String?


}