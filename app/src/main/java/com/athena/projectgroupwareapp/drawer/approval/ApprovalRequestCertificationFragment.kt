package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestCertificationBinding
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestLeaveBinding

class ApprovalRequestCertificationFragment : Fragment() {
    val binding : FragmentApprovalRequestCertificationBinding by lazy { FragmentApprovalRequestCertificationBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCertiExt.setOnClickListener{
            binding.etCertiReason.visibility = View.VISIBLE
        }


        binding.btnFreeUpload.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle("상신하시겠습니까?")
                .setPositiveButton("상신") { _, _ ->}
                //결재함에 내 데이터를 저장해야함 .. 아직 작업전 일단 토스트
                .setNegativeButton("취소") { _, _ -> }
                .show()
        }//setOnClickListener

    }//onViewCreated

}