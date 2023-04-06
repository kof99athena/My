package com.athena.projectgroupwareapp.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.athena.projectgroupwareapp.login.LoginActivity
import com.athena.projectgroupwareapp.R

class SplashActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_acticity)

        var hendle = Handler(Looper.getMainLooper()).postDelayed(Runnable {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        },2000)
        //핸들러 작업 시 Looper.getMainLooper() 해줘야 에러가 나지않는다. 루퍼를 안쓰면 에러

    }


}