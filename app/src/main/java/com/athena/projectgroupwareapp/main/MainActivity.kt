package com.athena.projectgroupwareapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityMainBinding
import com.athena.projectgroupwareapp.drawer.CalendarActivity
import com.athena.projectgroupwareapp.drawer.ConfirmActivity
import com.athena.projectgroupwareapp.drawer.DailyActivity
import com.athena.projectgroupwareapp.drawer.SalaryActivity
import com.athena.projectgroupwareapp.main.tab1.Tab1Fragment
import com.athena.projectgroupwareapp.main.tab2.Tab2Fragment
import com.athena.projectgroupwareapp.main.tab3.Tab3Fragment
import com.google.android.material.snackbar.Snackbar

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



        binding.drawerHbg.setOnClickListener{
            binding.drawerLayout.openDrawer(binding.relativeUp)
        }// 햄버거버튼을 누르면 서랍이 나온다.


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

        binding.setting.setOnClickListener{
            Snackbar.make(binding.drawerLayout,"권한이 없습니다. \n관리자에게 문의하세요.",Snackbar.LENGTH_INDEFINITE).setAction("X", {}).show()
        }



    }//onCreate



}