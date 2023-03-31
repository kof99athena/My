package com.athena.projectgroupwareapp.main.tab3.business

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1teamSalesBinding
import com.athena.projectgroupwareapp.main.tab3.business.recycler.SalesAdapter
import com.athena.projectgroupwareapp.main.tab3.business.recycler.SalesItem

class Tab1teamSalesFragment : Fragment() {
    lateinit var binding : FragmentTab1teamSalesBinding

    var salesitems : MutableList<SalesItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1team_sales,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTab1teamSalesBinding.bind(view)

        salesitems.add(SalesItem(R.drawable.pro1,"김혜영","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro2,"김민수","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro3,"김지은","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro4,"김형준","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro5,"박지영","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro6,"박준형","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro1,"이채연","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro2,"이준수","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro3,"이현이","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro4,"이주용","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro5,"한지은","02-0222-2832"))
        salesitems.add(SalesItem(R.drawable.pro6,"한태윤","02-0222-2832"))

        binding.recyclerSales.adapter = SalesAdapter(requireActivity(),salesitems)
        binding.recyclerSales.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)


    }


}