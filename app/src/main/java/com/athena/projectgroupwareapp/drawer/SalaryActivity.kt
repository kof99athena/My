package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityApprovalBinding
import com.athena.projectgroupwareapp.databinding.ActivitySalaryBinding
import com.google.android.material.snackbar.Snackbar

class SalaryActivity : AppCompatActivity() {

    val binding : ActivitySalaryBinding by lazy { ActivitySalaryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val til = binding.textInput  //TextInputLayout을 잡고있는녀석
        var s = til.editText?.text.toString() //TextInputLayout에서 잡고있는 녀석을 글씨로가져온다.


        var sort : Array<String> = resources.getStringArray(R.array.salary) //array에 등록된 결재종류들
        

        val adapter = ArrayAdapter.createFromResource(this,R.array.salary,android.R.layout.simple_list_item_1)
        binding.salarySelect.setAdapter(adapter)



        binding.salarySelect.setOnItemClickListener { parent, view, position, id ->
            var s : String = binding.salarySelect.text.toString()
            Log.i("tag",s)

            when{
                s.equals("2022년 급여명세서")->{
                    Snackbar.make(binding.framelayoutSalary,"22년 급여명세서는 관리자에게 문의하세요.", Snackbar.LENGTH_INDEFINITE).setAction("X", {}).show()
                }
                else -> false
            }

        }//onItemCLickListener
    }
}