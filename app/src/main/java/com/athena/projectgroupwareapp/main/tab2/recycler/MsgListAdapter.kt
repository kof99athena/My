package com.athena.projectgroupwareapp.main.tab2.recycler

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.main.tab2.chatting.ChattingActivity
import com.athena.projectgroupwareapp.main.tab3.IdCardActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

//리스트모양으로 만든 리사이클러뷰
class MsgListAdapter constructor(var context : Context, var items : MutableList<MessageListItem>) : RecyclerView.Adapter<MsgListAdapter.VH>() {

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
        var item : MessageListItem = items[position]

        holder.name.setText(item.name)
        holder.message.setText(item.message)
        holder.date.setText(item.date)
        holder.num.setText(item.num)
        Glide.with(context).load(item.imgID).into(holder.iv)

        holder.itemView.setOnClickListener {

            val intent : Intent = Intent(context, ChattingActivity::class.java)

            intent.putExtra("name",item.name)
            intent.putExtra("time",item.date)
            intent.putExtra("message",item.message)
            intent.putExtra("imgUrl",item.imgID)
            Log.i("name",item.name.toString())

            context.startActivity(intent)

        }


    }


}