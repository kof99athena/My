package com.athena.projectgroupwareapp.main.tab1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1Binding
import com.athena.projectgroupwareapp.databinding.FragmentTab3Binding
import com.athena.projectgroupwareapp.login.G
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.firebase.firestore.FirebaseFirestore

class Tab1Fragment : Fragment() {

    lateinit var binding: FragmentTab1Binding

    lateinit var totalitems: MutableList<TotalItem>
    lateinit var myteamitems: MutableList<MyteamItem>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("states","onCreateView")
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("states","onViewCreated")

        totalNotification(view)
        myteamNotification(view)


        Log.i("my",myteamitems.toString())
        Log.i("total",totalitems.size.toString())

    }

//
//    override fun onDestroy() {
//
//        totalitems.clear()
//        myteamitems.clear()
//        super.onDestroy()
//
//    }
//


    fun totalNotification(view: View) {
        binding = FragmentTab1Binding.bind(view)
        totalitems = mutableListOf()


        binding.viewTotalall.setOnClickListener {
            Toast.makeText(context, "잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

        //묵시적 인텐트로 넘기기 해보기

        var firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("notificationTotal").get().addOnSuccessListener {
            //Log.i("ahn111111","dddd")

            for (snapshot in it.documents) {
                //Log.i("ahn11111111111","dddd")

                var title: String = snapshot.get("title").toString()
                var date: String = snapshot.get("date").toString()
                var icon: String = snapshot.get("icon").toString()
                var url : String = snapshot.get("url").toString()


                totalitems.add(TotalItem(title, icon, date, url))
                Log.i("my",totalitems.size.toString())
            }

            binding.recyclerMain1.adapter = TotalAdapter(requireActivity(), totalitems)
            binding.recyclerMain1.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    fun myteamNotification(view: View) {
        binding = FragmentTab1Binding.bind(view)
        myteamitems = mutableListOf()

        binding.viewMyteamall.setOnClickListener {
            Toast.makeText(context, "잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

        var firebase: FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("notificationMy").whereEqualTo("team", G.employeeAccount?.team).get().addOnSuccessListener {

            for (snapshot in it.documents) {

                var title: String = snapshot.get("title").toString()
                var date : String = snapshot.get("date").toString()
                var icon : String = snapshot.get("icon").toString()
                var url  : String = snapshot.get("url").toString()

                myteamitems.add(MyteamItem(title, icon, date, url))
            }

            binding.recyclerMain2.adapter = MyteamAdapter(requireActivity(), myteamitems)
            binding.recyclerMain2.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        }

    }
}



