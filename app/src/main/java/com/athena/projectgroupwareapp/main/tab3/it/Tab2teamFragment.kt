package com.athena.projectgroupwareapp.main.tab3.it

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab2teamBinding
import com.google.android.material.tabs.TabLayoutMediator

class Tab2teamFragment : Fragment() {

    lateinit var binding : FragmentTab2teamBinding
    var tabtitle = listOf<String>("앱개발팀","AI팀","정보보안팀")
    lateinit var mediator: TabLayoutMediator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2team,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab2teamBinding.bind(view)

        var pager = binding.pagerIt
        var tabLayout = binding.tabLayoutIt

        binding.pagerIt.adapter = ItAdapter(requireActivity())
        mediator = TabLayoutMediator(tabLayout,pager, TabLayoutMediator.TabConfigurationStrategy(){tab, position ->
            tab.setText(tabtitle[position])
        })
        //중재자에게 탭 레이아웃에 맞은 pager를 붙이고, 탭 레이아웃은 list에있는 글씨를 따오게하자

        mediator.attach()
    }




}