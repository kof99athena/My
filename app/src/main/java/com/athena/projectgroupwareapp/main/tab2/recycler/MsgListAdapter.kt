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
import com.athena.projectgroupwareapp.main.tab2.chatting.GU
import com.athena.projectgroupwareapp.main.tab2.chatting.OtherAccount
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
        val id : TextView by lazy { itemView.findViewById(R.id.id) }
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
        holder.id.setText(item.otherId)
        Glide.with(context).load(item.imgID).into(holder.iv)

        holder.itemView.setOnClickListener {

            val intent : Intent = Intent(context, ChattingActivity::class.java)

            //선생님 : 인텐트를 누를때 GU변수를 아이템뷰의 사원으로 바꿔줘야한다.
            //넘기는 페이지에 따로 뭔가 전달하지 않아도 된다.

            //class OtherAccount constructor(var name : String, var imgProfile : String, var id : String, var chatId : String)

                var othername = item.name
                var otherid = item.otherId

            Log.i("ahn1111",item.otherId.toString())
            Log.i("ahn1111",item.name.toString())

            GU.otherAccount = OtherAccount(othername,"",otherid)

            context.startActivity(intent)

        }


    }


}