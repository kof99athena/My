package com.athena.projectgroupwareapp.main.tab3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.athena.projectgroupwareapp.main.tab3.business.Tab1teamFragment
import com.athena.projectgroupwareapp.main.tab3.management.Tab3teamFragment
import com.athena.projectgroupwareapp.main.tab3.it.Tab2teamFragment

class PersonAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    //var fragments : MutableList<Fragment> = mutableListOf()
    //MutableList은 인터페이스이므로 바로 쓸수없다. MutableList를 상속받은 mutableListOf()써야한다.

   var frag = listOf(Tab1teamFragment(), Tab2teamFragment(), Tab3teamFragment())

    //    var fragments : MutableList<Fragment> = mutableListOf(Tab1teamFragment(),Tab2teamFragment(),Tab3teamFragment())
  //코틀린에서 배열은 MutableList<--->로 만든다. 만들고 객체에 프래그먼트를 바로넣어준다.

    override fun getItemCount(): Int {
       return frag.size
    }

    override fun createFragment(position: Int): Fragment {
        return frag[position]
    }

}