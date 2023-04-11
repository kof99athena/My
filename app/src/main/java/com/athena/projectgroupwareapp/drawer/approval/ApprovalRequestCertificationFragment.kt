package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.size
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestCertificationBinding
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestLeaveBinding
import com.google.firebase.firestore.FirebaseFirestore

class ApprovalRequestCertificationFragment : Fragment() {
    val binding: FragmentApprovalRequestCertificationBinding by lazy { FragmentApprovalRequestCertificationBinding.inflate(layoutInflater) }
    lateinit var firebase : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }//onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //여기에서 값을 부르는게 아니다. 상신버튼을 눌렀을 때 그때의 String값을 가져오는 것이다.
        btnFreeUpload()

    }//onViewCreated

    //상신버튼 눌렀을 때 메소드
    fun btnFreeUpload(){
        binding.btnFreeUpload.setOnClickListener {

            var sort = binding.sort.checkedRadioButtonId
            var submit = binding.reason.checkedRadioButtonId


            when(sort) {
                R.id.employment -> { Toast.makeText(requireActivity(), binding.employment.text.toString(), Toast.LENGTH_SHORT).show() }
                R.id.receipt -> { Toast.makeText(requireActivity(), binding.receipt.text.toString(), Toast.LENGTH_SHORT).show() }
            }


            when(submit){
                R.id.sbm_public->{ Toast.makeText(requireActivity(), binding.sbmPublic.text.toString(), Toast.LENGTH_SHORT).show() }
                R.id.sbm_bank->{ Toast.makeText(requireActivity(), binding.sbmBank.text.toString(), Toast.LENGTH_SHORT).show() }
                R.id.sbm_company->{ Toast.makeText(requireActivity(), binding.sbmCompany.text.toString(), Toast.LENGTH_SHORT).show() }
            }

            AlertDialog.Builder(requireActivity())
                .setTitle("상신하시겠습니까?")
                .setPositiveButton("상신") { _, _ ->
                    firebase = FirebaseFirestore.getInstance()
                    firebase.collection("certification").document("증명서발급").get().addOnSuccessListener {

                    }
                }
                //결재함에 내 데이터를 저장해야함 .. 아직 작업전 일단 토스트
                .setNegativeButton("취소") { _, _ -> }
                .show()
        }//setOnClickListener
    }

}

