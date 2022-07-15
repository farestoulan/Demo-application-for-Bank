package com.example.roomapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.database.UserDatabase
import com.example.roomapp.entitys.*
import com.example.roomapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val readUserAllData: LiveData<List<User>>
    private val readDepositAllData: LiveData<List<DepositEntity>>
    private val readWithdrawAllData: LiveData<List<Withdraw>>
    val messageOne = MutableLiveData<String>()
    val messageTwo = MutableLiveData<String>()



    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val employeeDao = UserDatabase.getDatabase(application).employeeDao()
        val clientDao = UserDatabase.getDatabase(application).clientDao()
        val depositDao = UserDatabase.getDatabase(application).depositDao()
        val withdrawDao = UserDatabase.getDatabase(application).withdrawDao()


        repository = UserRepository(userDao, employeeDao, clientDao, depositDao ,withdrawDao)
        readUserAllData = repository.readAllDataUser
        readDepositAllData = repository.readAllDataDeposit
        readWithdrawAllData = repository.readAllDataWithdraw
    }


    fun returnDate(id: Int) {
        repository.returnDate(id)
    }
    fun returnMoneySource(id: Int):String{
     return   repository.returnMoneySource(id)
    }

    fun returnUpdateWithdraw(a: String, b: Int) {
        return repository.returnUpdateWithdraw(a, b)
    }


    fun updatePendingBalance(pendingBalance:Int,   id: Int)  {
          repository.updatePendingBalance(pendingBalance,id)
    }


    fun returnPendingBalance(id: Int) :Int {
        return  repository.returnPendingBalance(id)
    }

    fun returnBalanceAmount(id: Int) :Int {
      return  repository.returnBalance(id)
    }

    fun returnBalance(amount_Balance: Int, id: Int) {
          repository.returnUpdatbalance(amount_Balance,id)
    }
    fun updateApprovedDate(approved_Date: String, id: Int){
        repository.updateApprovedDate(approved_Date ,id)
    }

    fun updateEmployeeName(employeeName: String, id: Int){
        repository.updateEmployeeName(employeeName ,id)
    }

    fun updateApprovedDateWithdraw(approved_Date: String, id: Int){
        repository.updateApprovedDateWithdraw(approved_Date ,id)
    }

    fun returnClientID(clientID:Int):Int {
       return  repository.returnClientID(clientID)
    }

    fun returnEmployeeID(employee_ID :Int):Int{
        return repository.returnEmployeeID(employee_ID)
    }

    fun returnUpdate(a: String, b: Int) {
        return repository.returnUpdate(a, b)
    }
    fun returnEmployeeName(  id: Int):String{
        return repository.returnEmployeeName(id)
    }

    fun returnStatus(id: Int): String {
        return repository.returnStatus(id)
    }

    fun addUser(user: User): Long {

        return repository.addUser(user)


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

    fun addDeposit(deposit: DepositEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDeposit(deposit)

        }
    }
    fun getValues(x:String,y:String)
    {
      messageOne.value = x

        messageTwo.value = y

    }
    fun addWithdraw(withdrawEntity: Withdraw) {

           repository.addWithdraw(withdrawEntity)

    }
}