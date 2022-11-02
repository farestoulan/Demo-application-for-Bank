package com.example.roomapp.presentation.test_task_eng_khaled

import com.example.roomapp.data.models.dAO.WithdrawDAO
import java.util.*


class QueueTest(list: LinkedList<WithdrawDAO.BalanceAmountCreditTypesWithdraw?>){

    var items: LinkedList<WithdrawDAO.BalanceAmountCreditTypesWithdraw?> = list

     fun isEmpty():Boolean = items.isEmpty()

    fun size():Int = items.count()

    override  fun toString() = items.toString()

    fun enqueue(element:Any){
        items.add(element as WithdrawDAO.BalanceAmountCreditTypesWithdraw)
    }

    fun dequeue(): WithdrawDAO.BalanceAmountCreditTypesWithdraw?{
        if (this.isEmpty()){
            return null
        } else {
            return items.removeAt(0)
        }
    }

    fun peek():Any?{
        return items[0]
    }
}