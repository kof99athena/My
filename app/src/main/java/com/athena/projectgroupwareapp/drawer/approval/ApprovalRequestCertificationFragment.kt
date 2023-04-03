package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestCertificationBinding
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestLeaveBinding

class ApprovalRequestCertificationFragment : Fragment() {
    val binding: FragmentApprovalRequestCertificationBinding by lazy {
        FragmentApprovalRequestCertificationBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }//onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        certiExt()
        btnFreeUpload()

    }//onViewCreated

    //증명서 발급 시 기타를 눌렀을때 사유가 나오는 메소드
    fun certiExt(){
        binding.etCertiExt.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    binding.etCertiReason.visibility = View.VISIBLE
                }else{
                    binding.etCertiReason.visibility = View.GONE
                    binding.etCertiReason.setText("")
                }
            }

        }) //setOnCheckedChangeListener
    }

    //상신버튼 눌렀을 때 메소드
    fun btnFreeUpload(){
        binding.btnFreeUpload.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("상신하시겠습니까?")
                .setPositiveButton("상신") { _, _ -> }
                //결재함에 내 데이터를 저장해야함 .. 아직 작업전 일단 토스트
                .setNegativeButton("취소") { _, _ -> }
                .show()
        }//setOnClickListener
    }

}

