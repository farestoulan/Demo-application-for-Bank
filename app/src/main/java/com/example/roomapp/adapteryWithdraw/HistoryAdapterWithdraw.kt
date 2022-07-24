package com.example.roomapp.adapteryWithdraw

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.daoWithdraw.WithdrawDAO

class HistoryAdapterWithdraw(private val mContext: Context, mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?
) :
    RecyclerView.Adapter<HistoryAdapterWithdraw.MyViewHolder>() {
    private lateinit var mClickListener: ItemClickListenerWithdraw


    private var mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?

    interface ItemClickListenerWithdraw {
        fun onItemClick(view: View, position: Int, mData: List<WithdrawDAO.BalanceAmountCreditTypesHistoryWithdraw?>?)
    }

    fun setClickListener(itemClickListenerWithdraw: ItemClickListenerWithdraw) {
        mClickListener = itemClickListenerWithdraw
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_list_history, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = mData?.get(position)
        obj?.let { item ->
            holder.tvAmountHistory.text = item.value_Withdraw.toString()
            holder.tvEmployeeNameHistory.text = item.userName
            holder.tvTeansactionIDHistory.text = item.transactionID
            holder.tvRequestDAte.text = item.requestDate
            holder.tvApprovedDate.text=item.approvedDate
            holder.tvEmployeeNamee.text=item.employeeName



        }
    }


    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvTeansactionIDHistory: TextView
        var tvEmployeeNameHistory: TextView
        var tvAmountHistory: TextView
        var tvRequestDAte:TextView
        var tvApprovedDate:TextView
        var tvEmployeeNamee:TextView




        init {
            tvTeansactionIDHistory = itemView.findViewById(R.id.tv_TransactionIDHistory)
            tvEmployeeNameHistory = itemView.findViewById(R.id.tv_EmployeeNameHistory)
            tvAmountHistory = itemView.findViewById(R.id.tv_AmountHistory)
            tvRequestDAte = itemView.findViewById(R.id.tvRequestDate)
            tvApprovedDate = itemView.findViewById(R.id.tvApprovedDate)
            tvEmployeeNamee = itemView.findViewById(R.id.tvEmployeeNamee)
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            p0?.let { mClickListener.onItemClick(it, position,mData ) }
        }
    }

    init {
        this.mData = mData
    }
}