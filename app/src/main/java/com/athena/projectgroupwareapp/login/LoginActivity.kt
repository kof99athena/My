package com.athena.projectgroupwareapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.size
import com.athena.projectgroupwareapp.main.MainActivity
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityDailyBinding
import com.athena.projectgroupwareapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        }//onCreate

    private fun clickSignIn() {
        //이 함수를 호출하면 로그인과 관련된 실행문이 작동한다. 즉
        //파이어베이스에 있는 ID와 Password가 일치하면 로그인하도록한다.

        var id : String = binding.etIdnum.toString()

    }





}//LoginActivity




