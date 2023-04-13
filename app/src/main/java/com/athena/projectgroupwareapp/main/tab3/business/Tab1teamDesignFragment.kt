package com.athena.projectgroupwareapp.main.tab3.business

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1teamDesignBinding
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalAdapter
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalItem
import com.google.firebase.firestore.FirebaseFirestore


class Tab1teamDesignFragment : Fragment() {

    val binding : FragmentTab1teamDesignBinding by lazy { FragmentTab1teamDesignBinding.inflate(layoutInflater) }
    var personnalItems : MutableList<PersonnalItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("employeeList").whereEqualTo("team","디자인팀").get().addOnSuccessListener {

            for(snapshot in it.documents){
                var name : String = snapshot.get("name").toString()
                var tel : String = snapshot.get("tel").toString()
                var email : String = snapshot.get("email").toString()
                var imgUri : String = snapshot.get("profileUrl").toString()
                var id : String = snapshot.get("id").toString()

                personnalItems.add(PersonnalItem(imgUri, name , email, tel, id))
            }
            binding.recyclerPerson.adapter = PersonnalAdapter(requireActivity(),personnalItems)
            binding.recyclerPerson.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
        }

    }
}