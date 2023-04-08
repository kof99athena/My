package com.athena.projectgroupwareapp.main.tab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityIdCardBinding
import com.bumptech.glide.Glide

class IdCardActivity : AppCompatActivity() {

    lateinit var binding : ActivityIdCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_item_detail)
        binding = ActivityIdCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

       var name : String? = intent.getStringExtra("name")
       var email : String? = intent.getStringExtra("email").toString()
       var tel : String? = intent.getStringExtra("tel") as String
       var imgId : String? = intent.getStringExtra("imgId")
        //여기서 말하는 "imgId"은 파이어베이스 필드가 아니라 아답터의 putExtra("imgId",item.imgId)의 name이다.

        Glide.with(this).load(imgId).into(binding.iv)
        binding.name.setText(name)
        binding.tel.setText(tel)
        binding.email.setText(email)

        Log.i("email",email.toString())
        Log.i("email",name.toString())
        Log.i("email",imgId.toString())
    }

}