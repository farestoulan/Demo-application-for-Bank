package com.example.roomapp.data

import androidx.lifecycle.LiveData


class UserRepository(
    private val userDao: UserDao,
    private val employeeDao: EmployeeDao,
    private val clientDao: ClientDao

) {
    val readAllDataUser: LiveData<List<User>> = userDao.readAllDataUser()


    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }


    suspend fun addClient(client: ClientEntity) {
        clientDao.addclient(client)
    }


    suspend fun addEmployee(employee: Employee) {
        employeeDao.addemployee(employee)
    }


}
