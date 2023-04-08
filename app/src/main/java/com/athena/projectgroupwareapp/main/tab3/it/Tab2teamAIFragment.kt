package com.athena.projectgroupwareapp.main.tab3.it

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab2teamAIBinding
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalAdapter
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalItem
import com.google.firebase.firestore.FirebaseFirestore


class Tab2teamAIFragment : Fragment() {

    lateinit var binding : FragmentTab2teamAIBinding
    var personalItems : MutableList<PersonnalItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2team_a_i,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTab2teamAIBinding.bind(view)//보여줄 뷰에 바로 붙여라

        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection("employee").document("ITHeadquarters").collection("AI").get().addOnSuccessListener {

            for(snapshot in it.documents){
                var name : String = snapshot.get("name").toString()
                var email : String = snapshot.get("email").toString()
                var tel : String = snapshot.get("tel").toString()
                var imgUri : String = snapshot.get("profileUrl").toString()
                personalItems.add(PersonnalItem(imgUri, name , email, tel))
            }
                binding.recyclerPerson.adapter = PersonnalAdapter(requireActivity(), personalItems )
                binding.recyclerPerson.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        }

    }

}