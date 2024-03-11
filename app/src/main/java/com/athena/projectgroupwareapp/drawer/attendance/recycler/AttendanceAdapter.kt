package com.athena.projectgroupwareapp.drawer.attendance.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.drawer.approval.ApprovalRequestFragment
import com.athena.projectgroupwareapp.drawer.approval.ApprovalResultListFragment
import com.athena.projectgroupwareapp.features.login.G
import com.athena.projectgroupwareapp.main.tab3.IdCardActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot


//조직도의 리스트를 보여주는 아답터, 잡고있는 item을 연결하여 개인 IDcard로 넘겨준다.
class AttendanceAdapter constructor(var context : Context, var items : MutableList<AttendanceItem>) : Adapter<AttendanceAdapter.VH>() {

    lateinit var itemView :View

    var sort : String = String()
    var submit : String = String()

    //Firebase에 있는 데이터를 가져오자

    inner class VH(itemView: View) : ViewHolder(itemView){
        val today : TextView by lazy { itemView.findViewById(R.id.tv_atten_date) }
        val timeIn : TextView by lazy { itemView.findViewById(R.id.tv_atten_in) }
        val timeOut : TextView by lazy { itemView.findViewById(R.id.tv_atten_out) }
        val num : TextView by lazy { itemView.findViewById(R.id.tv_atten_num) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_attendance,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: VH, position: Int) {

        var item : AttendanceItem = items[position]

        holder.today.text = item.today
        holder.timeIn.text = item.timeIn
        holder.timeOut.text = item.timeOut
        holder.num.text = "${items.size-position}"

    }

}
