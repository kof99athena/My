package com.athena.projectgroupwareapp.main.tab1

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.bumptech.glide.Glide

class MyteamAdapter constructor(var context: Context, var items : MutableList<MyteamItem>) : Adapter<MyteamAdapter.VH>() {

    inner class VH(itemView: View) : ViewHolder(itemView){

        val title : TextView by lazy { itemView.findViewById(R.id.tv_total_title) }
        val date : TextView by lazy { itemView.findViewById(R.id.tv_total_date) }
        val iv : ImageView by lazy { itemView.findViewById(R.id.iv_total) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        //코틀린은 리스트의 get()대신 배열처럼 []를 선호한다
        var item : MyteamItem = items[position]
        holder.title.setText(item.title)
        holder.date.setText(item.date)
        Glide.with(context).load(item.imgId).into(holder.iv)


    }
}













