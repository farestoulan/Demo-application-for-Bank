package com.example.roomapp.adaptery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.dao.DepositDao.BalanceAmountCreditTypes

class Adapter(private val mContext: Context, mData: MutableList<BalanceAmountCreditTypes?>?) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private lateinit var mClickListener: ItemClickListener
    private var mData: MutableList<BalanceAmountCreditTypes?>?

    interface ItemClickListener {
        fun onItemClick(id: Int)
        fun itemClick(id: Int)
        fun itemWattingClick(id: Int)
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        mClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_list_deposit_request, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = mData?.get(position)
        obj?.let { item ->
            holder.deposit_txt.text = item.value_Deposit.toString()
            holder.currency_txt.text = item.creditType
            holder.name_txt.text = item.userName
            holder.acciept.setOnClickListener { mClickListener.onItemClick(position) }
            holder.reject.setOnClickListener { mClickListener.itemClick(position) }
            holder.watting.setOnClickListener { mClickListener.itemWattingClick(position) }



        }
    }
    fun removeAt(position: Int) {
        mData?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mData?.size!!)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name_txt: TextView
        var currency_txt: TextView
        var deposit_txt: TextView
        var acciept: Button
        var reject: Button
        var watting :Button

        init {
            name_txt = itemView.findViewById(R.id.tv_Name)
            currency_txt = itemView.findViewById(R.id.tv_Currency)
            deposit_txt = itemView.findViewById(R.id.tv_Deposit)
            acciept = itemView.findViewById(R.id.btn_Acciept)
            reject = itemView.findViewById(R.id.btn_Reject)
            watting =itemView.findViewById(R.id.btnWatting)
        }
    }

    init {
        this.mData = mData
    }

}