package com.athena.projectgroupwareapp.drawer.approval

import android.content.Intent
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
import androidx.fragment.app.FragmentManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestCertificationBinding
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestLeaveBinding
import com.athena.projectgroupwareapp.login.G
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class ApprovalRequestCertificationFragment : Fragment() {
    val binding: FragmentApprovalRequestCertificationBinding by lazy { FragmentApprovalRequestCertificationBinding.inflate(layoutInflater) }
    lateinit var firebase : FirebaseFirestore
    lateinit var chatRef : CollectionReference //컬렉션 참조(→)하는 변수

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
        binding.sort.setOnCheckedChangeListener(object : OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if(binding.certiExt.isChecked){
                    AlertDialog.Builder(requireActivity()).setMessage("관리자에게 문의하세요. ").show()
                }
            }
        })

        btnFreeUpload()

    }//onViewCreated

    //상신버튼 눌렀을 때 메소드
    fun btnFreeUpload(){

        binding.btnFreeUpload.setOnClickListener {

            var sort = binding.sort.checkedRadioButtonId
            var submit = binding.reason.checkedRadioButtonId

            var result1 : String = String()
            var result2 : String = String()


            when(sort) {
                R.id.employment -> { result1 = binding.employment.text.toString() }
                R.id.receipt -> { result1= binding.receipt.text.toString() }
            }

            when(submit){
                R.id.sbm_public->{ result2= binding.sbmPublic.text.toString() }
                R.id.sbm_bank->{ result2 = binding.sbmBank.text.toString() }
                R.id.sbm_company->{ result2= binding.sbmCompany.text.toString() }
            }

//            Log.i("result11111",result1)
//            Log.i("result11111",result2)

            AlertDialog.Builder(requireActivity())
                .setTitle("상신하시겠습니까?")
                .setPositiveButton("상신") { _, _ ->

                    var title  = binding.title.text.toString()
                    var name : String = G.employeeAccount?.name.toString()
                    var id : String = G.employeeAccount?.id.toString()

                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val today = sdf.format(Date())

                    var certiItem : CertiItem= CertiItem(title,result1,result2,id,name,"")

                    firebase = FirebaseFirestore.getInstance()
                    chatRef = firebase.collection("certification")
                    chatRef.document(today).set(certiItem)

                    Toast.makeText(requireActivity(), "상신완료", Toast.LENGTH_SHORT).show()

                   //childFragmentManager.beginTransaction().replace(R.id.framelayout_approval_result,ApprovalResultListFragment()).commit()
                }
                //결재함에 내 데이터를 저장해야함 .. 아직 작업전 일단 토스트
                .setNegativeButton("취소") { _, _ -> }
                .show()
        }//setOnClickListener
    }

    override fun onDestroy() {

        super.onDestroy()
    }

}

