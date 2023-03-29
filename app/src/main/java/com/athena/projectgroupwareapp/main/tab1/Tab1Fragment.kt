package com.athena.projectgroupwareapp.main.tab1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1Binding
import com.athena.projectgroupwareapp.databinding.FragmentTab3Binding
import com.google.android.material.bottomappbar.BottomAppBar

class Tab1Fragment : Fragment() {

    lateinit var binding : FragmentTab1Binding

    var items : MutableList<TotalItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTab1Binding.bind(view)

        items.add(TotalItem("전 직원 상반기 워크샵 안내 ",R.drawable.icon_new,"2023/03/29"))
        items.add(TotalItem("2023년 직장인 건강보험 실시(홀수년도) ",R.drawable.icon_must,"2023/03/18"))
        items.add(TotalItem(" 국내 최초 타임머신 기술 특허 출허 (과기부장관 상장) ",R.drawable.icon_congra,"2023/02/22"))
        items.add(TotalItem("연말정산 안내 (~1/31일까지)",R.drawable.icon_new,"2023/01/05"))
        items.add(TotalItem("근로계약서 작성 안내 및 개인면담 실시 (~1/31일까지)",R.drawable.icon_must,"2023/01/05"))
        items.add(TotalItem("12월 연말 시무식안내 (12/26일) ",R.drawable.icon_new,"2022/12/14"))
        items.add(TotalItem("백데이터 이관 작업 안내 ",R.drawable.icon_new,"2022/12/11"))

        binding.recyclerMain1.adapter = TotalAdapter(requireActivity(),items)
        binding.recyclerMain1.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

    }

}