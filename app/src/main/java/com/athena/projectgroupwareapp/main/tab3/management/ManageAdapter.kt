package com.athena.projectgroupwareapp.main.tab3.management

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ManageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    var flag = listOf(Tab3teamHRFragment(),Tab3teamComplianceFragment(),Tab3teamFinancialFragment())
    override fun getItemCount(): Int {
       return flag.size
    }

    override fun createFragment(position: Int): Fragment {
       return flag[position]
    }


}