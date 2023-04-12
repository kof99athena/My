package com.athena.projectgroupwareapp.drawer.approval

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentApprovalRequestBinding


class ApprovalRequestFragment : Fragment() {

    val binding : FragmentApprovalRequestBinding by lazy { FragmentApprovalRequestBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root // Approval_request_Fragment 만들고나서 보여줘야할 뷰는?
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val til = binding.textInput  //TextInputLayout을 잡고있는녀석
        var s = til.editText?.text.toString() //TextInputLayout에서 잡고있는 녀석을 글씨로가져온다.

        //val acTv = binding.approvalSelect //AutoCompleteTextView를 잡고있는 녀석
        var sort : Array<String> = resources.getStringArray(R.array.sort) //array에 등록된 결재종류들
       //Toast.makeText(requireActivity(), "${sort.size}", Toast.LENGTH_SHORT).show()

        //var adapter = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,sort)
        val adapter = ArrayAdapter.createFromResource(requireActivity(),R.array.sort,android.R.layout.simple_list_item_1)
        binding.approvalSelect.setAdapter(adapter)

        //Array 아탑더에게 simple_list_item_1로 sort에 있는 글자들을 한줄씩 보이게 한다.
        //simple_list_item_1는 안드로이드에 이미 준비가 되어있는 레이아웃이다.
        // simple_list_item_1 : 텍스트뷰 하나로 구성된 레이아웃

        // lateinit var freeFragment : Confirm_free_Fragment하고 초기화해줘야한다.


        binding.approvalSelect.setOnItemClickListener { parent, view, position, id ->
            var s : String = binding.approvalSelect.text.toString()
            Log.i("tag",s)

            when{
                s.equals("휴가원")->{
                    childFragmentManager.beginTransaction().replace(R.id.framelayout_approval_request,ApprovalRequestLeaveFragment()).commit()
                }

                s.equals("증명서발급")->{
                    childFragmentManager.beginTransaction().replace(R.id.framelayout_approval_request,ApprovalRequestCertificationFragment()).commit()
                }
                else -> false
            }



        }//onItemCLickListener





    }


}