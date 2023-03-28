package com.athena.projectgroupwareapp.main

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityMainBinding
import com.athena.projectgroupwareapp.drawer.CalendarActivity
import com.athena.projectgroupwareapp.drawer.ConfirmActivity
import com.athena.projectgroupwareapp.drawer.DailyActivity
import com.athena.projectgroupwareapp.drawer.SalaryActivity

class MainActivity : AppCompatActivity() {

    lateinit var tab1Fragment : Tab1Fragment
    lateinit var tab2Fragment : Tab2Fragment
    lateinit var tab3Fragment : Tab3Fragment
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tab1Fragment= Tab1Fragment()
        tab2Fragment= Tab2Fragment()
        tab3Fragment= Tab3Fragment()

        supportFragmentManager.beginTransaction().replace(R.id.framelayout,tab1Fragment).commit()

        binding.navBottom.setOnItemSelectedListener{item->
            when(item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout,tab1Fragment).commit()
                    true
                }

                R.id.message -> {
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout,tab2Fragment).commit()
                    true
                }
                R.id.people ->{
                    supportFragmentManager.beginTransaction().replace(R.id.framelayout,tab3Fragment).commit()
                    true
                }
                else -> false
            }
        }

        binding.menuSalary.setOnClickListener{
            var intent : Intent = Intent(this@MainActivity,SalaryActivity::class.java)
            startActivity(intent)
        }

        binding.menuDaily.setOnClickListener{
            var intent : Intent = Intent(this@MainActivity,DailyActivity::class.java)
            startActivity(intent)
        }

        binding.menuConfirm.setOnClickListener{
            var intent : Intent = Intent(this@MainActivity,ConfirmActivity::class.java)
            startActivity(intent)
        }

        binding.menuCalendar.setOnClickListener{
            var intent : Intent = Intent(this@MainActivity,CalendarActivity::class.java)
            startActivity(intent)
        }


    }//onCreate
}