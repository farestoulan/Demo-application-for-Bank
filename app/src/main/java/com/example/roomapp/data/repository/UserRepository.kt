package com.example.roomapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomapp.data.models.dAO.*
import com.example.roomapp.data.models.dAO.daoUser.UserDao
import com.example.roomapp.data.models.dAO.daoUser.UserTuple
import com.example.roomapp.data.models.entitys.*
import com.example.roomapp.data.test_task_eng_khaled.users.Admin
import com.example.roomapp.data.test_task_eng_khaled.users.Customer
import com.example.roomapp.data.test_task_eng_khaled.users.employees.HeadOffice
import com.example.roomapp.data.test_task_eng_khaled.users.employees.RegularEmployee


class UserRepository(
    private val userDao: UserDao,
    private val employeeDao: EmployeeDao,
    private val clientDao: ClientDao,
    private val depositDao: DepositDao,
    private val withdrawDao : WithdrawDAO,
    private val branchesDAO: BranchesDAO,
    private val departmentDAO: DepartmentDAO,
    private val rulesDAO: RulesDAO,
    private val regularEmployee: RegularEmployee,
    private val customer: Customer,
    private val admin: Admin,
   private val headOffice: HeadOffice
) {
    val readAllDataDeposit: LiveData<List<DepositEntity>> = depositDao.readAllDeposit()
    val readAllDataWithdraw: LiveData<List<Withdraw>> = withdrawDao.readAllWithdraw()
    val readAllBranches: LiveData<List<Branches>> = branchesDAO.readAllBranches()
    val readAllDepartments:LiveData<List<Departments>> = departmentDAO.readAllDepartments()
    val readAllRoles:LiveData<List<Roles>> = rulesDAO.readAllRules()

    fun returnID(  id: Int):Int{
        return userDao.returnID(id)
    }


    fun logIn(email: String , password: String): List<UserTuple>{
        return userDao.logIn(email,password)
    }


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

    fun addBranch(branches: Branches) :Long {
   return  branchesDAO.addBranches(branches)
    }

    fun addDepartment(departments: Departments):Long {
        return departmentDAO.addDepartments(departments)
    }

    fun addRules(roles: Roles) {
        rulesDAO.addRules(roles)
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

     fun getMyName(context: Context,id :Int):String?{
       return regularEmployee.getMyName(context,id)
    }
    fun getMyEmail(context: Context,id :Int):String?{
        return regularEmployee.getMyEmail(context,id)
    }

    fun getMyPassword(context: Context,id :Int):String?{
        return regularEmployee.getMyPassword(context,id)
    }

    fun getMyNameClient(context: Context,id :Int):String?{
        return customer.getMyName(context,id)
    }
    fun getMyEmailClient(context: Context,id :Int):String?{
        return customer.getMyEmail(context,id)
    }

    fun getMyPasswordClient(context: Context,id :Int):String?{
        return customer.getMyPassword(context,id)
    }


    fun getMyNameAdmin(context: Context,id :Int):String?{
        return admin.getMyName(context,id)
    }
    fun getMyEmailAdmin(context: Context,id :Int):String?{
        return admin.getMyEmail(context,id)
    }

    fun getMyPasswordAdmin(context: Context,id :Int):String?{
        return admin.getMyPassword(context,id)
    }


    fun getMyNameBoss(context: Context,id :Int):String?{
        return headOffice.getMyName(context,id)
    }
    fun getMyEmailBoss(context: Context,id :Int):String?{
        return headOffice.getMyEmail(context,id)
    }

    fun getMyPasswordBoss(context: Context,id :Int):String?{
        return headOffice.getMyPassword(context,id)
    }
}
