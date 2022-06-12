package com.example.roomapp.adaptery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.dao.DepositDao
import com.example.roomapp.dao.EmployeeDao

class HistoryAdapter (private val mContext: Context, mData: List<DepositDao.BalanceAmountCreditTypesHistory?>?) :
    RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private var mData: List<DepositDao.BalanceAmountCreditTypesHistory?>?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_list_history, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = mData?.get(position)
        obj?.let { item ->
            holder.tvDepositHistory.text = item.value_Deposit.toString()
            holder.tvCurrencyHistory.text = item.creditType
            holder.tvNameHistory.text = item.userName


        }
    }


    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvNameHistory: TextView
        var tvCurrencyHistory: TextView
        var tvDepositHistory: TextView


        init {
            tvNameHistory = itemView.findViewById(R.id.tv_NameHistory)
            tvCurrencyHistory = itemView.findViewById(R.id.tv_CurrencyHistory)
            tvDepositHistory = itemView.findViewById(R.id.tv_DepositHistory)

        }
    }

    init {
        this.mData = mData
    }}