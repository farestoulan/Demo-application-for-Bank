package com.example.roomapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.Relationships.ClientWithDeposits
import com.example.roomapp.Relationships.UserAndClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val readUserAllData: LiveData<List<User>>

    private val readDepositAllData: LiveData<List<DepositEntity>>
    private val readClientWithDeposits:List<ClientWithDeposits>
 //  private val readUserandClient:List<UserAndClient>



    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val employeeDao = UserDatabase.getDatabase(application).employeeDao()
        val clientDao = UserDatabase.getDatabase(application).clientDao()
        val depositDao = UserDatabase.getDatabase(application).depositDao()

        repository = UserRepository(userDao, employeeDao, clientDao,depositDao)
        readUserAllData = repository.readAllDataUser

        readDepositAllData=repository.readAllDataDeposit
        readClientWithDeposits=repository.readDataClientWithDeposits
      //  readUserandClient=repository.readDataUserandClient



    }



    fun returnClientandDeposits():List<ClientWithDeposits>{
        return repository.returndataClientWithDeposts()
    }

//    fun returnUserAndClient():List<UserAndClient>{
//        return repository.returnUserandClient()
//    }

    fun addUser(user: User):Long {

      return  repository.addUser(user)


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
}