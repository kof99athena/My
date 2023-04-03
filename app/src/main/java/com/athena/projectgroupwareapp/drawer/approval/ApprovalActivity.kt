package com.athena.projectgroupwareapp.drawer.approval

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityApprovalBinding
import com.athena.projectgroupwareapp.drawer.CalendarActivity


class ApprovalActivity : AppCompatActivity() {

    private lateinit var binding : ActivityApprovalBinding
    //private lateinit var requestFragment: ApprovalRequestFragment
    //val a : Int by lazy { 1 } 메소드에 불러지지 않으면 만들어지않는다. { }안에는 a에 들어갈 값을 넣어주면된다 [ex : 1 or ApprovalRequestFragment()]

    val requestFragment = ApprovalRequestFragment() //미리 객체를 만들어서 쓰자!
    val resultListFragment = ApprovalResultListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_confirm)
            binding = ActivityApprovalBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //객체를 만들고나서 함수를 써야한다. 객체 만들기 :  requestFragment = Approval_request_Fragment()  멤버변수에 적어줬다.
            supportFragmentManager.beginTransaction().replace(R.id.framelayout_approval,requestFragment).commit()

            binding.btnRequest.setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_approval,requestFragment).commit()
            }

            binding.btnApprovalResult.setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.framelayout_approval,resultListFragment).commit()
            }

        }//onCreate

    }
