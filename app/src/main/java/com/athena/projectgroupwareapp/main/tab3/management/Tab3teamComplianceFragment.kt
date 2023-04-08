package com.athena.projectgroupwareapp.main.tab3.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab3teamComplianceBinding
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalAdapter
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalItem
import com.google.firebase.firestore.FirebaseFirestore

class Tab3teamComplianceFragment : Fragment() {

    lateinit var binding : FragmentTab3teamComplianceBinding
    var personnalItem : MutableList<PersonnalItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab3team_compliance,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTab3teamComplianceBinding.bind(view)

        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("employee").document("ManageHeadquarters").collection("compliance").get().addOnSuccessListener {
            for(snapshot in it.documents){
                var name : String = snapshot.get("name").toString()
                var email : String = snapshot.get("email").toString()
                var tel : String = snapshot.get("tel").toString()
                var imgUri : String = snapshot.get("profileUrl").toString()
                personnalItem.add(PersonnalItem(imgUri, name , email, tel))

            }
            binding.recyclerPerson.adapter = PersonnalAdapter(requireActivity(),personnalItem)
            binding.recyclerPerson.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        }

    }

}
