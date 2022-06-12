package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.dao.*
import com.example.roomapp.entitys.*


class UserRepository(
    private val userDao: UserDao,
    private val employeeDao: EmployeeDao,
    private val clientDao: ClientDao,
    private val depositDao: DepositDao,
    private val withdrawDao :WithdrawDAO


) {
    val readAllDataUser: LiveData<List<User>> = userDao.readAllDataUser()
    val readAllDataDeposit: LiveData<List<DepositEntity>> = depositDao.readAllDeposit()
    val readAllDataWithdraw: LiveData<List<Withdraw>> = withdrawDao.readAllWithdraw()

    // val readStatus :String =depositDao.returnStatus()



    fun returnUpdateWithdraw(a:String,b:Int){
        return withdrawDao.update(a,b)
    }

    fun returnBalance(id: Int) :Int{
      return  clientDao.returnBalance(id)
    }


    fun returnUpdatbalance(amount_Balance: Int, id: Int){
         clientDao.updateBalance(amount_Balance ,id)
    }

    fun returnClientID(client_ID :Int):Int{
        return clientDao.returnClientID(client_ID)
    }

    fun returnStatus(id:Int):String{
        return depositDao.returnStatus(id)
    }
    fun returnUpdate(a:String,b:Int){
        return depositDao.update(a,b)
    }


     fun addUser(user: User):Long {
      return  userDao.addUser(user)
    }


    suspend fun addClient(client: ClientEntity) {
        clientDao.addclient(client)
    }


    suspend fun addEmployee(employee: Employee) {
        employeeDao.addemployee(employee)
    }

    suspend fun addDeposit(depositEntity: DepositEntity) {
       depositDao.addDeposit(depositEntity)
    }
     fun addWithdraw(withdrawEntity: Withdraw) {
       withdrawDao.addWithdraw(withdrawEntity)
    }


}
