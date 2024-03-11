package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalResultListBinding
import com.athena.projectgroupwareapp.drawer.approval.recycler.CertiListAdapter
import com.athena.projectgroupwareapp.drawer.approval.recycler.CertiListItem
import com.athena.projectgroupwareapp.features.login.G
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ApprovalResultListFragment : Fragment() {
    val binding : FragmentApprovalResultListBinding by lazy{ FragmentApprovalResultListBinding.inflate(layoutInflater)}
    var certiListItem : MutableList<CertiListItem> = mutableListOf()

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

        firebase.collection("certification").whereEqualTo("id", G.employeeAccount?.id).get()
            .addOnSuccessListener {
            for(snapshot in it.documents){
                var title : String = snapshot.get("title").toString()
                var date : String = snapshot.get("dateOfIssue").toString()

                Log.i("size",date)
                Log.i("nameby", G.employeeAccount?.id.toString())

                certiListItem.add(CertiListItem(title,date))
            }
            //var a : List<CertiListItem> = certiListItem.reversed()
                //certiListItem.reverse()
            binding.recyclerCertiList.adapter = CertiListAdapter(requireActivity(),certiListItem.reversed().toMutableList())
            binding.recyclerCertiList.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL, false)
        }




    }
}