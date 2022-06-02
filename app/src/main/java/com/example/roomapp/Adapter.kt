package com.example.roomapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.Relationships.ClientWithDeposits

class Adapter(private val mContext: Context, mData: List<ClientWithDeposits>) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var mData :List<ClientWithDeposits>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val v: View
        val inflater = LayoutInflater.from(mContext)
        v = inflater.inflate(R.layout.fragment_item_list_deposit_request, parent, false)


        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


//       holder.name_txt.text = mData[position].client.
        holder.currency_txt.text = mData[position].client.creditType
       holder.deposit_txt.text = mData[position].deposit.get(position).value_Deposit




    }

    override fun getItemCount(): Int {
        return mData.size

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_txt: TextView
        var currency_txt: TextView
        var deposit_txt:TextView

        init {
            name_txt = itemView.findViewById(R.id.tv_Name)
            currency_txt = itemView.findViewById(R.id.tv_Currency)
            deposit_txt = itemView.findViewById(R.id.tv_Deposit)


        }
    }


    init {
        this.mData = mData
    }



}