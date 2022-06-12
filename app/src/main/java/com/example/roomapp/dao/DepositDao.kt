package com.example.roomapp.dao

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


    @Query("UPDATE add_Deposit SET status_Request=:statusRequest  WHERE id = :id")
    fun update(statusRequest: String, id: Int)


    @Query(
        "SELECT add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName " +
                "FROM add_Deposit, table_Client, user_table WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and status_Request != 'Accept' and  status_Request != 'Reject'"
    )
    fun loadMixedData(): List<BalanceAmountCreditTypes?>?

    data class BalanceAmountCreditTypes(
        val deposit_Id: Int,
        val clientID: Int,
        val value_Deposit: Int,
        val creditType: String?,
        val userName: String?
    )

    @Query(
        "SELECT add_Deposit.id AS deposit_Id ,add_Deposit.client_ID AS clientID , add_Deposit.value_Deposit AS value_Deposit , table_Client.creditType AS creditType , user_table.name AS userName  " +
                "FROM add_Deposit, table_Client, user_table WHERE add_Deposit.client_ID = table_Client.id and table_Client.user_ID = user_table.id and(status_Request = 'Accept' OR  status_Request = 'Reject') "
    )
    fun loadMixedDataHistory(): List<BalanceAmountCreditTypesHistory?>?

    data class BalanceAmountCreditTypesHistory(
        val deposit_Id: Int,
        val clientID: Int,
        val value_Deposit: Int,
        val creditType: String?,
        val userName: String?

    )


}