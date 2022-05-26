package com.example.roomapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val selectItem = MutableLiveData<String>()


    fun setData(item: String) {
        selectItem.value = item
    }


    fun getSelectItem(): MutableLiveData<String>? {
        return selectItem
    }




}