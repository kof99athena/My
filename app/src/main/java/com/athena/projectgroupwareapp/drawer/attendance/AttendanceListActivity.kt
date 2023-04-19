package com.athena.projectgroupwareapp.drawer.attendance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.databinding.ActivityAttendanceListBinding
import com.athena.projectgroupwareapp.drawer.approval.recycler.CertiListAdapter
import com.athena.projectgroupwareapp.drawer.attendance.recycler.AttendanceAdapter
import com.athena.projectgroupwareapp.drawer.attendance.recycler.AttendanceItem
import com.athena.projectgroupwareapp.login.G
import com.google.firebase.firestore.FirebaseFirestore

/**
 * 나의 출퇴근 내역이 나오는 화면 - firebase 구조를 잘 짜야함.
 */
class AttendanceListActivity : AppCompatActivity() {

    //lateinit var binding : com.athena.projectgroupwareapp.databinding.ActivityAttendanceListBinding
    lateinit var binding : com.athena.projectgroupwareapp.databinding.ActivityAttendanceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("test1111","하잉")


        var attendanceItem : MutableList<AttendanceItem> = mutableListOf()
        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()

        firebase.collection("attendance").document(G.employeeAccount?.id.toString())
            .collection("attendance")
            .get()
            .addOnSuccessListener {


                for(snapshot in it.documents){
                    var today : String = snapshot.get("today").toString()
                    var timeIn : String = snapshot.get("timeIn").toString()
                    var timeOut : String = snapshot.get("timeOut").toString()

                    attendanceItem.add(AttendanceItem(today, timeIn, timeOut))

                }

                //var a : List<CertiListItem> = certiListItem.reversed()
                //certiListItem.reverse()
                binding.recyclerAttenList.adapter = AttendanceAdapter(this,attendanceItem.reversed().toMutableList())
                binding.recyclerAttenList.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false)
            }






    }
}