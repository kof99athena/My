package com.athena.projectgroupwareapp.main.tab3.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1teamBinding
import com.athena.projectgroupwareapp.databinding.FragmentTab3teamBinding
import com.google.android.material.tabs.TabLayoutMediator

class Tab3teamFragment : Fragment() {

    lateinit var binding: FragmentTab3teamBinding
    var tabtitle = listOf<String>("HR팀","컴플라이언스팀","회계팀")
    lateinit var mediator: TabLayoutMediator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab3team,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab3teamBinding.bind(view)

        var pager = binding.pagerManage
        var tabLayout = binding.tabLayoutManage

        binding.pagerManage.adapter = ManageAdapter(requireActivity())
        mediator = TabLayoutMediator(tabLayout,pager, TabLayoutMediator.TabConfigurationStrategy(){tab, position ->
            tab.setText(tabtitle[position])
        })
        mediator.attach()
    }
}