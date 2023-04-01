package com.athena.projectgroupwareapp.main.tab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    lateinit var binding : ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_notification)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title : String? = intent.getStringExtra("title")
        var date : String? = intent.getStringExtra("date")

        binding.notificationTitle.setText(title)
        binding.notificationDate.setText(date)

    }
}