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

        binding.btnLogin.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        }//onCreate





}//LoginActivity




