package com.athena.projectgroupwareapp.main.tab3.business.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.athena.projectgroupwareapp.R
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class SalesAdapter constructor(var context : Context , var items : MutableList<SalesItem>) : Adapter<SalesAdapter.VH>() {


    inner class VH(itemView: View) : ViewHolder(itemView){

        val name : TextView by lazy { itemView.findViewById(R.id.name) }
        val tel : TextView by lazy { itemView.findViewById(R.id.tell) }
        val iv : ImageView by lazy { itemView.findViewById(R.id.iv_person) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.recycler_item_person,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item : SalesItem = items[position]
        holder.name.setText(item.name)
        holder.tel.setText(item.tel)
        Glide.with(context).load(item.imgId).into(holder.iv)

    }


}