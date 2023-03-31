package com.athena.projectgroupwareapp.main.tab3.business

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BusinessAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

   var frag = listOf(Tab1teamSalesFragment(),Tab1teamCSFragment(),Tab1teamDesignFragment())

    override fun getItemCount(): Int {
        return frag.size
    }

    override fun createFragment(position: Int): Fragment {
       return frag[position]
    }


}