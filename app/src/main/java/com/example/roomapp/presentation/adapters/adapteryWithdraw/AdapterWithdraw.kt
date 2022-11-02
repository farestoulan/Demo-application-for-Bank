package com.example.roomapp.presentation.adapters.adapteryWithdraw

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.data.models.dAO.WithdrawDAO

class AdapterWithdraw(private val wContext: Context, wData: MutableList<WithdrawDAO.BalanceAmountCreditTypesWithdraw?>) :
    RecyclerView.Adapter<AdapterWithdraw.MyViewHolder>() {
    private lateinit var wClickListener: ItemClickListenerWithdraw
    private var wData: MutableList<WithdrawDAO.BalanceAmountCreditTypesWithdraw?>

    interface ItemClickListenerWithdraw {
        fun onItemClick(id: Int)
        fun itemClick(id: Int)
    }

    fun setClickListener(itemClickListenerWithdraw: ItemClickListenerWithdraw) {
        wClickListener = itemClickListenerWithdraw
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(wContext)
        v = inflater.inflate(R.layout.fragment_item_list_withdraw, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = wData.get(position)
        obj?.let { item ->
            holder.withdraw_txt.text = item.value_Withdraw.toString()
            holder.currency_txt.text = item.creditType
            holder.name_txt.text = item.userName

            if(position ==0){
                holder.acciept.isEnabled = true
                holder.reject.isEnabled = true
            }else {
                holder.acciept.isEnabled = false
                holder.reject.isEnabled = false
            }

            holder.acciept.setOnClickListener { wClickListener.onItemClick(position) }
            holder.reject.setOnClickListener { wClickListener.itemClick(position) }

        }
    }

    fun dequeue(){
        wData.removeAt(0)
        notifyItemRemoved(0)
        notifyItemChanged(0)
    }

    fun removeAt(position: Int) {
        wData.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, wData.size)
    }

    override fun getItemCount(): Int {
        return wData.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name_txt: TextView
        var currency_txt: TextView
        var withdraw_txt: TextView
        var acciept: Button
        var reject: Button

        init {
            name_txt = itemView.findViewById(R.id.tv_Name_Withdraw)
            currency_txt = itemView.findViewById(R.id.tv_Currency_Withdraw)
            withdraw_txt = itemView.findViewById(R.id.tv_Withdraw)
            acciept = itemView.findViewById(R.id.btn_Acciept_Withdraw)
            reject = itemView.findViewById(R.id.btn_Reject_Withdraw)
        }
    }

    init {
        this.wData = wData
    }


}
