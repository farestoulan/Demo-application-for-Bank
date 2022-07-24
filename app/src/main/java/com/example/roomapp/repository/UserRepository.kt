package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.daoClient.ClientDao
import com.example.roomapp.daoDeposit.*
import com.example.roomapp.daoEmployee.EmployeeDao
import com.example.roomapp.daoUser.UserDao
import com.example.roomapp.daoWithdraw.WithdrawDAO
import com.example.roomapp.entitys.*


class UserRepository(
    private val userDao: UserDao,
    private val employeeDao: EmployeeDao,
    private val clientDao: ClientDao,
    private val depositDao: DepositDao,
    private val withdrawDao : WithdrawDAO


) {
    val readAllDataUser: LiveData<List<User>> = userDao.readAllDataUser()
    val readAllDataDeposit: LiveData<List<DepositEntity>> = depositDao.readAllDeposit()
    val readAllDataWithdraw: LiveData<List<Withdraw>> = withdrawDao.readAllWithdraw()

    // val readStatus :String =depositDao.returnStatus()


    fun returnDate(id: Int){
        depositDao.returnDate(id)
    }
    fun returnMoneySource(id: Int):String{
       return depositDao.returnMoneySource(id)
    }

    fun returnUpdateWithdraw(a:String,b:Int){
        return withdrawDao.update(a,b)
    }


    fun updatePendingBalance(pendingBalance:Int,   id: Int) {
          clientDao.updatePendingBalance(pendingBalance,id)
    }


    fun returnPendingBalance(id: Int) :Int{
        return  clientDao.returnPendingBalance(id)
    }


    fun returnBalance(id: Int) :Int{
      return  clientDao.returnBalance(id)
    }


    fun returnUpdatbalance(amount_Balance: Int, id: Int){
         clientDao.updateBalance(amount_Balance ,id)
    }
    fun updateApprovedDate(approved_Date: String, id: Int){
        depositDao.updateApprovedDate(approved_Date ,id)
    }

    fun updateEmployeeName(employeeName: String, id: Int){
        depositDao.updateEmployeeName(employeeName ,id)
    }

    fun updateEmployeeID(employeeID: Int, id: Int){
        depositDao.updateEmployeeID(employeeID ,id)
    }

    fun employeeName(employeeName: String, id: Int){
        withdrawDao.employeeName(employeeName ,id)
    }

    fun updateEmployeeIDWithdraw(employeeID: Int, id: Int){
        withdrawDao.updateEmployeeID(employeeID ,id)
    }

    fun updateApprovedDateWithdraw(approved_Date: String, id: Int){
        withdrawDao.updateApprovedDateWithdraw(approved_Date ,id)
    }

    fun returnClientID(client_ID :Int):Int{
        return clientDao.returnClientID(client_ID)
    }

    fun returnEmployeeID(employee_ID :Int):Int{
        return employeeDao.returnEmployeeID(employee_ID)
    }



    fun returnEmployeeName(  id: Int):String{
        return userDao.returnEmployeeName(id)
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
