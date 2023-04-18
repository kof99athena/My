package com.athena.projectgroupwareapp.main.tab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.athena.projectgroupwareapp.databinding.ActivityIdCardBinding
import com.athena.projectgroupwareapp.login.ChatName
import com.athena.projectgroupwareapp.login.G
import com.athena.projectgroupwareapp.main.tab2.chatting.ChattingActivity
import com.athena.projectgroupwareapp.main.tab2.chatting.GU
import com.athena.projectgroupwareapp.main.tab2.chatting.OtherAccount
import com.bumptech.glide.Glide

/**
 * 사원증
 */
class IdCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityIdCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIdCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name: String? = intent.getStringExtra("name")
        var email: String? = intent.getStringExtra("email").toString()
        var tel: String? = intent.getStringExtra("tel") as String
        var imgId: String? = intent.getStringExtra("imgId")
        var id: String? = intent.getStringExtra("id") //상대방 id
        //여기서 말하는 "imgId"은 파이어베이스 필드가 아니라 아답터의 putExtra("imgId",item.imgId)의 name이다.

        Glide.with(this).load(imgId).into(binding.iv)
        binding.name.setText(name)
        binding.tel.setText(tel)
        binding.email.setText(email)
        binding.id.setText(id)


//        Log.i("email",email.toString())
//        Log.i("email",name.toString())
//        Log.i("email",imgId.toString())

        binding.btnMessage.setOnClickListener {

            GU.otherAccount = OtherAccount(name.toString(), imgId.toString(), id.toString())

            var intent: Intent = Intent(this, ChattingActivity::class.java)
            startActivity(intent)
        }


    }

}