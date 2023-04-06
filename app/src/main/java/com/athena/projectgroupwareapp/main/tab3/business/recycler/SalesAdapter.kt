package com.athena.projectgroupwareapp.main.tab3.business.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.main.tab3.IdCardActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class SalesAdapter constructor(var context : Context , var items : MutableList<SalesItem>) : Adapter<SalesAdapter.VH>() {

    lateinit var itemView :View


    //Firebase에 있는 데이터를 가져오자



    inner class VH(itemView: View) : ViewHolder(itemView){

        val name : TextView by lazy { itemView.findViewById(R.id.name) }
        val tel : TextView by lazy { itemView.findViewById(R.id.tel) }
        val email : TextView by lazy { itemView.findViewById(R.id.email) }
        val iv : ImageView by lazy { itemView.findViewById(R.id.iv_person) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_person,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {





        var item : SalesItem = items[position]
        holder.name.setText(item.name)
        holder.tel.setText(item.tel)
        holder.email.setText(item.email)
        Glide.with(context).load(item.imgId).into(holder.iv)

        holder.itemView.setOnClickListener {

           val intent : Intent = Intent(context,IdCardActivity::class.java)
            intent.putExtra("name",item.name)
            intent.putExtra("tel",item.tel)
            intent.putExtra("email ",item.email)
            intent.putExtra("imgId",item.imgId)


            context.startActivity(intent)
        }

    }

}
