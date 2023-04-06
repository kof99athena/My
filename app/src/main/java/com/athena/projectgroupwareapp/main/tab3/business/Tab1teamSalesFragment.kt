package com.athena.projectgroupwareapp.main.tab3.business

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1teamSalesBinding
import com.athena.projectgroupwareapp.main.tab3.business.recycler.SalesAdapter
import com.athena.projectgroupwareapp.main.tab3.business.recycler.SalesItem
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

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

        //Firebase에 있는 데이터를 가져오자
        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("/employee/businessHeadquarters/sales").get().addOnSuccessListener {
            Log.i("size",it.documents.size.toString())

            for(snapshot in it.documents){
                var name : String = snapshot.get("name").toString()
                var tel : String = snapshot.get("tel").toString()
                var email : String = snapshot.get("email").toString()
                Log.i("tag1111",name+tel)

                salesitems.add(SalesItem(R.drawable.pro9, name , email, tel))
            }

            //아답터를 여기에 붙이는 이유는? 성공한 다음에 아답터를 불러와야하니까. 이 메소드 밖에 쓰면 이상하다.
            //왜? 불러오지도 않았는데 아답터를 쓰는건 잘못된거니까
            binding.recyclerSales.adapter = SalesAdapter(requireActivity(),salesitems)
            binding.recyclerSales.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        }.addOnFailureListener {
            //Log.i("size1",it.message.toString())
        }



    }


}