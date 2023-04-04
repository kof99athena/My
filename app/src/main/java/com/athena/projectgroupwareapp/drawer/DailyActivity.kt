package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.athena.projectgroupwareapp.R

class DailyActivity : AppCompatActivity() {
    //구글 gps를 이용하여 내 위치를 받아오자.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
    }
}