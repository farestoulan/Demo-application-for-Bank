package com.example.roomapp.data

import androidx.lifecycle.LiveData
import com.example.roomapp.Relationships.ClientWithDeposits
import com.example.roomapp.Relationships.UserAndClient


class UserRepository(
    private val userDao: UserDao,
    private val employeeDao: EmployeeDao,
    private val clientDao: ClientDao,
    private val depositDao: DepositDao


) {
    val readAllDataUser: LiveData<List<User>> = userDao.readAllDataUser()

    val readAllDataDeposit: LiveData<List<DepositEntity>> = depositDao.readAllDeposit()
    val readDataClientWithDeposits: List<ClientWithDeposits> = clientDao.getClientWithDeposits()
  // val readDataUserandClient:List<UserAndClient> = userDao.getUserAndClient()



    fun returndataClientWithDeposts():List<ClientWithDeposits>{
        return clientDao.getClientWithDeposits()
    }
//    fun returnUserandClient():List<UserAndClient>{
//        return userDao.getUserAndClient()
//    }

     fun addUser(user: User):Long {
      return  userDao.addUser(user)
    }


    suspend fun addClient(client: ClientEntity) {
        clientDao.addclient(client)
    }


    suspend fun addEmployee(employee: Employee) {
        employeeDao.addemployee(employee)
    }

    suspend fun addDeposit(depositentity: DepositEntity) {
       depositDao.addDeposit(depositentity)
    }


}
