package com.example.roomapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val readUserAllData: LiveData<List<User>>


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val employeeDao = UserDatabase.getDatabase(application).employeeDao()
        val clientDao = UserDatabase.getDatabase(application).clientDao()

        repository = UserRepository(userDao, employeeDao, clientDao)
        readUserAllData = repository.readAllDataUser


    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)

        }
    }


    fun addPersonalInformation(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employee)

        }
    }

    fun addAdditionalInformation(client: ClientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClient(client)

        }
    }
}