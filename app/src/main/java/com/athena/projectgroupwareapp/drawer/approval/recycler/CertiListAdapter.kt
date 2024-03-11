package com.athena.projectgroupwareapp.drawer.approval.recycler

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
class CertiListAdapter constructor(var context : Context, var items : MutableList<CertiListItem>) : Adapter<CertiListAdapter.VH>() {

    lateinit var itemView :View

    var sort : String = String()
    var submit : String = String()

    //Firebase에 있는 데이터를 가져오자

    inner class VH(itemView: View) : ViewHolder(itemView){
        val title : TextView by lazy { itemView.findViewById(R.id.tv_certi_title) }
        val date : TextView by lazy { itemView.findViewById(R.id.tv_certi_date) }
        val num : TextView by lazy { itemView.findViewById(R.id.tv_certi_num) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        itemView = LayoutInflater.from(context).inflate(R.layout.recycler_item_certification,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {

        var item : CertiListItem = items[position]

        holder.title.text = item.title
        holder.date.text = item.dateOfIssue
        holder.num.text = "${items.size-position}"

        holder.itemView.setOnClickListener {

            var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
            var ref : CollectionReference = firebase.collection("certification")



            //여기가 별로인거같음. holder.date.text.toString()
            //사번으로 찾고싶은데 사번으로 하면 제일 최신 데이터만 가져옴
            ref.whereEqualTo("dateOfIssue",holder.date.text.toString()).addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    //변경된 도큐먼트를 찾아달라고함
                    val documentChanges : MutableList<DocumentChange> = value!!.documentChanges

                    //처음 기존내용 가져오기
                    for(documentchange : DocumentChange in documentChanges){

                        //변경된 문서중에서 데이터 촬영한 Snapshot얻어오기
                        var snapshot : DocumentSnapshot = documentchange.document
                        var contents : MutableMap<String,Any>? = snapshot.data

                        sort = contents?.get("sort").toString()

                        submit =contents?.get("submit").toString()
                    }

                    Log.i("nameto",sort)
                    AlertDialog.Builder(context).setMessage("내용 : ${sort} \n제출처 : ${submit}").setPositiveButton("확인"){_,_-> }.show()
                }
            }) //스탭샷 리스너


        }

    }

}
