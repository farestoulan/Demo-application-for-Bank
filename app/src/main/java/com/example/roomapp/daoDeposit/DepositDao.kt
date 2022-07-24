package com.example.roomapp.daoDeposit

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.entitys.DepositEntity

@Dao
interface DepositDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDeposit(depositEntity: DepositEntity)

    @Query("SELECT * FROM add_Deposit ORDER BY id ASC")
    fun readAllDeposit(): LiveData<List<DepositEntity>>

    @Query("SELECT max(status_Request) as status_Request FROM add_Deposit WHERE id =:id  ")
    fun returnStatus(id: Int): String


    //Update status request
    @Query("UPDATE add_Deposit SET status_Request=:statusRequest  WHERE id = :id")
    fun update(statusRequest: String, id: Int)


    //Waiting for employee
    @Query(
        "SELECT  add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName " +
                "FROM add_Deposit, table_Client, user_table  WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id  and status_Request != 'Accept' and  status_Request != 'Reject' "
    )
    fun loadMixedData(): List<BalanceAmountCreditTypes?>?

    data class BalanceAmountCreditTypes(
        val deposit_Id: Int,
        val clientID: Int,
        val value_Deposit: Int,
        val creditType: String?,
        val userName: String?
    )

    //History



    @Query(
        "SELECT add_Deposit.employee_name As employeeName, add_Deposit.approved_Date As approvedDate, add_Deposit.transactionID As transactionID, add_Deposit.request_Date As requestDate, add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName " +
                "FROM add_Deposit, table_Client, user_table WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and  (status_Request = 'Accept' OR status_Request = 'Reject') and employee_ID=:id"
    )
    fun loadMixedDataHistory(id: Int): List<BalanceAmountCreditTypesHistory?>?

    data class BalanceAmountCreditTypesHistory(

        val deposit_Id: Int,
        val transactionID: String?,
        val clientID: Int,
        val value_Deposit: Int,
        val creditType: String?,
        val userName: String?,
        val requestDate: String?,
        val approvedDate: String?,
        val employeeName: String?


    )


//Query for list accept

    @Query(
        "SELECT add_Deposit.employee_name As employeeName, add_Deposit.approved_Date As approvedDate, add_Deposit.transactionID As transactionID, add_Deposit.request_Date As requestDate, add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName  " +
                "FROM add_Deposit, table_Client, user_table WHERE  add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and status_Request = 'Accept' and clientID = :id  "
    )
    fun loadMixedDataAccept(id: Int): List<BalanceAmountCreditTypesHistory?>?


    //Query for list pending

    @Query(
        "SELECT add_Deposit.employee_name As employeeName, add_Deposit.approved_Date As approvedDate, add_Deposit.transactionID As transactionID, add_Deposit.request_Date As requestDate, add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName  " +
                "FROM add_Deposit, table_Client, user_table WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and status_Request = 'Pending' and clientID = :id "
    )
    fun loadMixedDataPending(id: Int): List<BalanceAmountCreditTypesHistory?>?


    //Query for list reject

    @Query(
        "SELECT add_Deposit.employee_name As employeeName, add_Deposit.approved_Date As approvedDate, add_Deposit.transactionID As transactionID, add_Deposit.request_Date As requestDate, add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName  " +
                "FROM add_Deposit, table_Client, user_table WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and status_Request = 'Reject' and clientID = :id "
    )
    fun loadMixedDataReject(id: Int): List<BalanceAmountCreditTypesHistory?>?


    @Query("SELECT   request_Date from add_Deposit where id=:id ")
    fun returnDate(id: Int): Int

    @Query("UPDATE add_Deposit SET approved_Date=:approved_Date  WHERE id = :id")
    fun updateApprovedDate(approved_Date: String, id: Int)

    @Query("UPDATE add_Deposit SET employee_name=:employeeName  WHERE id = :id")
    fun updateEmployeeName(employeeName: String, id: Int)

    @Query("UPDATE add_Deposit SET employee_ID = :employee_ID  WHERE id = :id")
    fun updateEmployeeID(employee_ID :Int ,     id: Int)



    @Query("SELECT   money_source from add_Deposit where id=:id ")
    fun returnMoneySource(id: Int): String

}