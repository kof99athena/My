package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityCalendarBinding
import com.athena.projectgroupwareapp.drawer.attendance.recycler.AttendanceAdapter
import com.athena.projectgroupwareapp.drawer.attendance.recycler.AttendanceItem
import com.athena.projectgroupwareapp.drawer.attendance.recycler.CalListItem
import com.athena.projectgroupwareapp.login.G
import com.athena.projectgroupwareapp.main.tab2.chatting.MyItem
import com.google.android.material.textfield.TextInputLayout.AccessibilityDelegate
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.FileOutputStream

class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding
    var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var calendarRef : CollectionReference //컬렉션 참조(→)하는 변수
    lateinit var calendarRef2 : DocumentReference //컬렉션/도큐먼트를 참조(→)하는 변수
    
    lateinit var scd_month : String
    lateinit var scd_dayodMonth : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_calendar)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.etTask.visibility = View.VISIBLE
            binding.save.visibility = View.VISIBLE
            binding.delete.visibility = View.VISIBLE

            Log.i("확인",year.toString()+(month+1).toString()+dayOfMonth.toString())


            scd_month = month.toString()
            scd_dayodMonth = dayOfMonth.toString()

            binding.save.setOnClickListener {
                calendarRef = firebase.collection("calendar")
                calendarRef2 = firebase.collection("calendar")
                    .document(G.employeeAccount?.id.toString())// 안에 필드가없으면 도큐먼트 사이즈가 0이나온다. 하나는 넣어주자.

                var date : String = year.toString()+"년 "+(month+1).toString()+"월 "+dayOfMonth.toString()+"일 "
                var memo : String = binding.etmemo.text.toString()

                Log.i("menoStrin",memo)
                Log.i("날짜0",date)

                var calendarItem : CalListItem = CalListItem(memo,date)
                calendarRef.document(G.employeeAccount?.id.toString()).collection("calendar").document(date).set(calendarItem)

                var myitem : MyItem = MyItem(G.employeeAccount?.name.toString())
                calendarRef2.set(myitem)
                Toast.makeText(this, "저장되었습니다. ", Toast.LENGTH_SHORT).show()

            }





            
        }

        binding.delete.setOnClickListener {
            binding.etTask.visibility = View.GONE
            binding.save.visibility = View.GONE
            binding.delete.visibility = View.GONE
        }

        var CalListItemItem : MutableList<CalListItem> = mutableListOf()

        firebase.collection("calendar").document(G.employeeAccount?.id.toString())
            .collection("calendar")
            .get()
            .addOnSuccessListener {


                for(snapshot in it.documents){
                    var dateOfIssue : String = snapshot.get("dateOfIssue").toString()
                    var title : String = snapshot.get("title").toString()


                    CalListItemItem.add(CalListItem(title, dateOfIssue))

                }

                //var a : List<CertiListItem> = certiListItem.reversed()
                //certiListItem.reverse()
                binding.recyclerCalendarList.adapter = CalendarAdapter(this,CalListItemItem.reversed().toMutableList())
                binding.recyclerCalendarList.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false)
            }


    }
}
