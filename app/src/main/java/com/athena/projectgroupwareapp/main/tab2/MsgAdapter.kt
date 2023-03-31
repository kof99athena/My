package com.athena.projectgroupwareapp.main.tab2

import android.content.Context
import android.graphics.ImageDecoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.bumptech.glide.Glide

class MsgAdapter constructor(var context : Context, var items : MutableList<MessageItem>) : RecyclerView.Adapter<MsgAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView) {
        val name : TextView by lazy { itemView.findViewById(R.id.name) }
        val message : TextView by lazy { itemView.findViewById(R.id.message) }
        val date : TextView by lazy { itemView.findViewById(R.id.date) }
        val num : TextView by lazy { itemView.findViewById(R.id.num) }
        val iv : ImageView by lazy { itemView.findViewById(R.id.iv_person) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.recycler_item_message,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item : MessageItem = items[position]
        holder.name.setText(item.name)
        holder.message.setText(item.message)
        holder.date.setText(item.date)
        holder.num.setText(item.num)
        Glide.with(context).load(item.imgID).into(holder.iv)
    }


}