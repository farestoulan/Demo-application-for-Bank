package com.example.roomapp.presentation.test_task_eng_khaled

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R

class AdapterHorizontalRecycler( private var imageView: List<Image>) : RecyclerView.Adapter<AdapterHorizontalRecycler.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_update_home,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindView(imageView[position])


    }

    override fun getItemCount(): Int {
        return imageView.size
    }

  inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      var images :ImageView
      fun bindView(image: Image){
          images.setImageResource(image.imageSrc)

      }

      init {
          images = itemView.findViewById(R.id.imageView4)

      }


    }
}