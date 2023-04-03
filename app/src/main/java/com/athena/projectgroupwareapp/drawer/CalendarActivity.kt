package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityCalendarBinding
import com.google.android.material.textfield.TextInputLayout.AccessibilityDelegate
import java.io.FileOutputStream

class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_calendar)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.etTask.visibility = View.VISIBLE
            binding.save.visibility = View.VISIBLE
            binding.delete.visibility = View.VISIBLE
        }

        binding.delete.setOnClickListener {
            binding.etTask.visibility = View.GONE
            binding.save.visibility = View.GONE
            binding.delete.visibility = View.GONE

        }
    }
}
