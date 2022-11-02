package com.example.roomapp.presentation.viewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.models.dAO.daoUser.UserTuple
import com.example.roomapp.data.data_source.local.database.UserDatabase
import com.example.roomapp.data.models.entitys.*
import com.example.roomapp.data.repository.UserRepository
import com.example.roomapp.data.test_task_eng_khaled.users.Admin
import com.example.roomapp.data.test_task_eng_khaled.users.Customer
import com.example.roomapp.data.test_task_eng_khaled.users.employees.HeadOffice
import com.example.roomapp.data.test_task_eng_khaled.users.employees.RegularEmployee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val readDepositAllData: LiveData<List<DepositEntity>>
    private val readWithdrawAllData: LiveData<List<Withdraw>>
    val messageOne = MutableLiveData<String>()
    val messageTwo = MutableLiveData<String>()
    private val readAllBranches: LiveData<List<Branches>>
    private val readAllDepartments: LiveData<List<Departments>>
    private val readAllRoles: LiveData<List<Roles>>


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val employeeDao = UserDatabase.getDatabase(application).employeeDao()
        val clientDao = UserDatabase.getDatabase(application).clientDao()
        val depositDao = UserDatabase.getDatabase(application).depositDao()
        val withdrawDao = UserDatabase.getDatabase(application).withdrawDao()
        val branchesDAO = UserDatabase.getDatabase(application).branchDao()
        val departmentDAO = UserDatabase.getDatabase(application).departmentDao()
        val rulesDAO = UserDatabase.getDatabase(application).rulesDao()
        val regularEmployee = RegularEmployee()
        val customer = Customer("","","",0,"","","" )
        val admin = Admin("","","",0,"","","")
        val headOffice = HeadOffice()

        repository = UserRepository(
            userDao,
            employeeDao,
            clientDao,
            depositDao,
            withdrawDao,
            branchesDAO,
            departmentDAO,
            rulesDAO,
            regularEmployee,
            customer,
            admin,
            headOffice
        )
        readDepositAllData = repository.readAllDataDeposit
        readWithdrawAllData = repository.readAllDataWithdraw
        readAllBranches = repository.readAllBranches
        readAllDepartments = repository.readAllDepartments
        readAllRoles = repository.readAllRoles
    }


    fun returnID(  id: Int):Int{
        return repository.returnID(id)
    }

    fun logIn(email: String , password: String): List<UserTuple>{
        return repository.logIn(email,password)
    }

    fun returnDate(id: Int) {
        repository.returnDate(id)
    }

    fun returnMoneySource(id: Int): String {
        return repository.returnMoneySource(id)
    }

    fun returnUpdateWithdraw(a: String, b: Int) {
        return repository.returnUpdateWithdraw(a, b)
    }


    fun updatePendingBalance(pendingBalance: Int, id: Int) {
        repository.updatePendingBalance(pendingBalance, id)
    }


    fun returnPendingBalance(id: Int): Int {
        return repository.returnPendingBalance(id)
    }

    fun returnBalanceAmount(id: Int): Int {
        return repository.returnBalance(id)
    }

    fun returnBalance(amount_Balance: Int, id: Int) {
        repository.returnUpdatbalance(amount_Balance, id)
    }

    fun updateApprovedDate(approved_Date: String, id: Int) {
        repository.updateApprovedDate(approved_Date, id)
    }

    fun updateEmployeeName(employeeName: String, id: Int) {
        repository.updateEmployeeName(employeeName, id)
    }

    fun updateEmployeeID(employeeID: Int, id: Int) {
        repository.updateEmployeeID(employeeID, id)
    }

    fun updateEmployeeIDWithdraw(employeeID: Int, id: Int) {
        repository.updateEmployeeID(employeeID, id)
    }

    fun UpdateEmployeeName(employeeName: String, id: Int) {
        repository.employeeName(employeeName, id)
    }

    fun updateApprovedDateWithdraw(approved_Date: String, id: Int) {
        repository.updateApprovedDateWithdraw(approved_Date, id)
    }

    fun returnClientID(clientID: Int): Int {
        return repository.returnClientID(clientID)
    }

    fun returnEmployeeID(employee_ID: Int): Int {
        return repository.returnEmployeeID(employee_ID)
    }


    fun returnUpdate(a: String, b: Int) {
        return repository.returnUpdate(a, b)
    }

    fun returnEmployeeName(id: Int): String {
        return repository.returnEmployeeName(id)
    }

    fun returnStatus(id: Int): String {
        return repository.returnStatus(id)
    }

    fun addUser(user: User): Long {
        return repository.addUser(user)
    }

    fun addDepartment(departments: Departments): Long {
        return repository.addDepartment(departments)
    }

    fun addRules(roles: Roles) {
        repository.addRules(roles)
    }


    fun addBranch(branches: Branches): Long {

        return repository.addBranch(branches)
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

    fun getValues(x: String, y: String) {
        messageOne.value = x

        messageTwo.value = y

    }

    fun addWithdraw(withdrawEntity: Withdraw) {

        repository.addWithdraw(withdrawEntity)
    }

    fun getMyEmail(context: Context, id: Int): String? {
        return repository.getMyEmail(context, id)
    }

    fun getMyPassword(context: Context, id: Int): String? {
        return repository.getMyPassword(context, id)
    }

    fun getMyName(context: Context, id: Int): String? {
        return repository.getMyName(context, id)
    }

    fun getMyEmailClient(context: Context, id: Int): String? {
        return repository.getMyEmailClient(context, id)
    }

    fun getMyPasswordClient(context: Context, id: Int): String? {
        return repository.getMyPasswordClient(context, id)
    }

    fun getMyNameClient(context: Context, id: Int): String? {
        return repository.getMyNameClient(context, id)
    }



    fun getMyEmailAdmin(context: Context, id: Int): String? {
        return repository.getMyEmailAdmin(context, id)
    }

    fun getMyPasswordAdmin(context: Context, id: Int): String? {
        return repository.getMyPasswordAdmin(context, id)
    }

    fun getMyNameAdmin(context: Context, id: Int): String? {
        return repository.getMyNameAdmin(context, id)
    }

    fun getMyEmailBoss(context: Context, id: Int): String? {
        return repository.getMyEmailBoss(context, id)
    }

    fun getMyPasswordBoss(context: Context, id: Int): String? {
        return repository.getMyPasswordBoss(context, id)
    }

    fun getMyNameBoss(context: Context, id: Int): String? {
        return repository.getMyNameBoss(context, id)
    }
}