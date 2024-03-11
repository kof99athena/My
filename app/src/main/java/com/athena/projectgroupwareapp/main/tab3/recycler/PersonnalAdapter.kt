package com.athena.projectgroupwareapp.main.tab3.recycler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.features.login.G
import com.athena.projectgroupwareapp.main.tab3.IdCardActivity
import com.bumptech.glide.Glide


//조직도의 리스트를 보여주는 아답터, 잡고있는 item을 연결하여 개인 IDcard로 넘겨준다.
class PersonnalAdapter constructor(var context : Context, var items : MutableList<PersonnalItem>) : Adapter<PersonnalAdapter.VH>() {

    lateinit var itemView :View


    //Firebase에 있는 데이터를 가져오자

    inner class VH(itemView: View) : ViewHolder(itemView){

        val name : TextView by lazy { itemView.findViewById(R.id.name) }
        val tel : TextView by lazy { itemView.findViewById(R.id.tel) }
        val email : TextView by lazy { itemView.findViewById(R.id.email) }
        val iv : ImageView by lazy { itemView.findViewById(R.id.iv_person) }
        val id : TextView by lazy { itemView.findViewById(R.id.id) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_person,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {


        var item : PersonnalItem = items[position]

        holder.name.setText(item.name)
        holder.tel.setText(item.tel)
        holder.email.setText(item.email)
        holder.id.setText(item.id)

        Glide.with(context).load(item.imgId).into(holder.iv)

        //Log.i("gahs",item.imgId.toString())

        holder.itemView.setOnClickListener {

           val intent : Intent = Intent(context,IdCardActivity::class.java)
            intent.putExtra("name",item.name)
            intent.putExtra("tel",item.tel)
            intent.putExtra("email",item.email)
            intent.putExtra("imgId",item.imgId)
            intent.putExtra("id",item.id)


            context.startActivity(intent)
        }

    }

}
