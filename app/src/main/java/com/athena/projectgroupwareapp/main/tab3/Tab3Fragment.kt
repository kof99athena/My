package com.athena.projectgroupwareapp.main.tab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab3Binding
import com.google.android.material.tabs.TabLayoutMediator

class Tab3Fragment : Fragment() {

    private lateinit var binding : FragmentTab3Binding

    var tabtitle = listOf<String>("사업부문","IT부문","관리부문")
    lateinit var mediator : TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab3,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab3Binding.bind(view) // binding을 초기화 해줘야한다. inflate가 필요없으니까 바로 bind 해준다.

        var pager = binding.pager
        var tabLayout = binding.tabLayout

        binding.pager.adapter= PersonAdapter(requireActivity()) //!!대신하는 함수 (내부적으로 null이 아닌것을 리턴해준다)


         mediator = TabLayoutMediator(tabLayout,pager, TabLayoutMediator.TabConfigurationStrategy(){tab,positon->
            tab.setText(tabtitle[positon])
        })

        mediator.attach()
    }


}