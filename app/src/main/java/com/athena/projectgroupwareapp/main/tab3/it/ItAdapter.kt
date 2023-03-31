package com.athena.projectgroupwareapp.main.tab3.it

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ItAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    var frag = listOf(Tab2teamAppFragment(),Tab2teamAIFragment(),Tab2teamItsecurityFragment())
    override fun getItemCount(): Int {
        return frag.size
    }

    override fun createFragment(position: Int): Fragment {
        return frag[position]
    }


}