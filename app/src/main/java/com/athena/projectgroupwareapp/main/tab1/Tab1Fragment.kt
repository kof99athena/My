package com.athena.projectgroupwareapp.main.tab1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1Binding
import com.athena.projectgroupwareapp.databinding.FragmentTab3Binding
import com.google.android.material.bottomappbar.BottomAppBar

class Tab1Fragment : Fragment() {

    lateinit var binding : FragmentTab1Binding

    var totalitems : MutableList<TotalItem> = mutableListOf()
    var myteamitems : MutableList<MyteamItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalNotification(view)
        myteamNotification(view)

        binding.viewTotalall.setOnClickListener {
            Toast.makeText(context, "잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

        binding.viewMyteamall.setOnClickListener {
            Toast.makeText(context, "잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
        }

    }

    fun totalNotification(view: View){
        binding = FragmentTab1Binding.bind(view)

        totalitems.add(TotalItem("전 직원 상반기 워크샵 안내 ",R.drawable.newitem,"2023/03/29"))
        totalitems.add(TotalItem("2023년 직장인 건강보험 실시(홀수년도) ",R.drawable.mustitem,"2023/03/18"))
        totalitems.add(TotalItem("국내 최초 타임머신 기술 특허 출허 \n(과기부장관 상장) ",R.drawable.congra,"2023/02/22"))
        totalitems.add(TotalItem("연말정산 안내 (~1/31일까지)",R.drawable.newitem,"2023/01/05"))
        totalitems.add(TotalItem("근로계약서 작성 안내 및 개인면담 실시 (~1/31일까지)",R.drawable.mustitem,"2023/01/05"))
        totalitems.add(TotalItem("12월 연말 시무식안내 (12/26일) ",R.drawable.newitem,"2022/12/14"))
        totalitems.add(TotalItem("백데이터 이관 작업 안내 ",R.drawable.newitem,"2022/12/11"))

        binding.recyclerMain1.adapter = TotalAdapter(requireActivity(),totalitems)
        binding.recyclerMain1.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    fun myteamNotification(view: View){
        binding = FragmentTab1Binding.bind(view)

        myteamitems.add(MyteamItem("인사파트 회의 (4/1 16시)",R.drawable.task,"2023/03/29"))
        myteamitems.add(MyteamItem("파트 회식있습니다. (4/5) ",R.drawable.task,"2023/03/18"))
        myteamitems.add(MyteamItem("안전관리에 유의 부탁드립니다. ",R.drawable.task,"2023/02/22"))
        myteamitems.add(MyteamItem("코로나 감염 예방에 최선을 다해주세요. ",R.drawable.task,"2023/01/05"))
        myteamitems.add(MyteamItem("출퇴근 관리 부탁드려요. 지각하지마세요.",R.drawable.task,"2023/01/05"))
        myteamitems.add(MyteamItem("시무식 참석 여부 확인해주세요",R.drawable.task,"2022/12/14"))
        myteamitems.add(MyteamItem("데이터 이관 확인하시고 사인해주세요. ",R.drawable.task,"2022/12/11"))

        binding.recyclerMain2.adapter = MyteamAdapter(requireActivity(), myteamitems)
        binding.recyclerMain2.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }
}