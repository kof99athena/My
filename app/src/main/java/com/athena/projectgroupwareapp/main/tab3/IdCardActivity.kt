package com.athena.projectgroupwareapp.main.tab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
       var tel : String? = intent.getStringExtra("tel") as String
       var imgId : Int = intent.getIntExtra("imgId", R.drawable.pro1)

        Glide.with(this).load(imgId).into(binding.iv)
        binding.name.setText(name)
        binding.tel.setText(tel)


    }
}