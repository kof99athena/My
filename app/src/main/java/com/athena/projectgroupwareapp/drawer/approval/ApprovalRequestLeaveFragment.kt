package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestLeaveBinding
import com.athena.projectgroupwareapp.features.login.G
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class ApprovalRequestLeaveFragment : Fragment() {
    val binding : FragmentApprovalRequestLeaveBinding by lazy { FragmentApprovalRequestLeaveBinding.inflate(layoutInflater) }
    lateinit var firebase : FirebaseFirestore
    lateinit var chatRef : CollectionReference //컬렉션 참조(→)하는 변수


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val today = sdf.format(Date())
        binding.date.text = today.toString()

        btnFreeUpload()

    }//onViewCreated

    //상신버튼 눌렀을 때 메소드
    fun btnFreeUpload(){

        binding.btnFreeUpload.setOnClickListener {

            var sort = binding.leaveSort.checkedRadioButtonId
            var submit = binding.leaveReason.checkedRadioButtonId

            var result1 : String = String()
            var result2 : String = String()


            when(sort) {
                R.id.all -> { result1 = binding.all.text.toString() }
                R.id.half -> { result1= binding.half.text.toString() }
            }

            when(submit){
                R.id.reason_personnal->{ result2= binding.reasonPersonnal.text.toString() }
                R.id.reason_hospital->{ result2 = binding.reasonHospital.text.toString() }
                R.id.reason_congra->{ result2= binding.reasonCongra.text.toString() }
            }

//            Log.i("result11111",result1)
//            Log.i("result11111",result2)

            AlertDialog.Builder(requireActivity())
                .setTitle("상신하시겠습니까?")
                .setPositiveButton("상신") { _, _ ->

                    var title  = binding.title.text.toString()
                    var name : String = G.employeeAccount?.name.toString()
                    var id : String = G.employeeAccount?.id.toString()
                    var hollydays : String = binding.hollyday.text.toString()

                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val today = sdf.format(Date())

                    var certiItem : CertiItem= CertiItem(title,result1,result2,id,name,hollydays,today)

                    firebase = FirebaseFirestore.getInstance()
                    chatRef = firebase.collection("certification")
                    chatRef.document(today).set(certiItem)

                    Toast.makeText(requireActivity(), "상신완료", Toast.LENGTH_SHORT).show()

                    //부모 프래그먼트를 지우고 오토컴플릿텍스트를 공백으로 만들어서 새로 나오는 느낌을 만들어준다.
                    parentFragmentManager.beginTransaction().remove(this).commit()
                    (parentFragment as ApprovalRequestFragment).binding.approvalSelect.setText("")


                }
                //결재함에 내 데이터를 저장해야함 .. 아직 작업전 일단 토스트
                .setNegativeButton("취소") { _, _ -> }
                .show()
        }//setOnClickListener
    }


}


