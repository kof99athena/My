package com.athena.projectgroupwareapp.main.tab3.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1teamBinding
import com.google.android.material.tabs.TabLayoutMediator

//영업
class
Tab1teamFragment : Fragment() {

    lateinit var binding : FragmentTab1teamBinding

    var tabtitle = listOf<String>("영업팀","CS팀","디자인팀") // 지금 초기화 작업 끝
    lateinit var mediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1team,container,false)
    }

    //fragment가 실행되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab1teamBinding.bind(view)

        var pager = binding.pagerBusiness
        var tapLayout = binding.tabLayoutBusiness

        binding.pagerBusiness.adapter = BusinessAdapter(requireActivity())
        mediator = TabLayoutMediator(tapLayout,pager,TabLayoutMediator.TabConfigurationStrategy(){tab, position ->
            tab.setText(tabtitle[position])
        })

        mediator.attach()


    }

}