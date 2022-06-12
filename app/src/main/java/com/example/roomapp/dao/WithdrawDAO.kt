package com.example.roomapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapp.entitys.Withdraw

@Dao
interface WithdrawDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addWithdraw(withdrawEntity: Withdraw)

    @Query("SELECT * FROM table_Withdraw ORDER BY id ASC")
    fun readAllWithdraw(): LiveData<List<Withdraw>>

    @Query("UPDATE table_Withdraw SET status_Request=:statusRequest  WHERE id = :id")
    fun update(statusRequest: String, id: Int)


    @Query(
        "SELECT table_Withdraw.id AS withdraw_Id ,table_Withdraw.client_ID AS clientID , table_Withdraw.value_Withdraw AS value_Withdraw , table_Client.creditType AS creditType , user_table.name AS userName " +
                "FROM table_Withdraw, table_Client, user_table WHERE table_Withdraw.client_ID = table_Client.id and table_Client.user_ID = user_table.id and status_Request != 'Accept' and  status_Request != 'Reject'"
    )
    fun loadMixedData(): List<BalanceAmountCreditTypesWithdraw?>?

    data class BalanceAmountCreditTypesWithdraw(
        val withdraw_Id: Int,
        val clientID:Int,
        val value_Withdraw: Int,
        val creditType: String?,
        val userName: String?
    )
}