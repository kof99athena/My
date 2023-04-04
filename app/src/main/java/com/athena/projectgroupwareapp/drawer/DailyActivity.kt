package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityDailyBinding
import com.kakao.util.maps.helper.Utility
import net.daum.mf.map.api.MapView

class DailyActivity : AppCompatActivity() {
    //카카오 맵을 추가하자
    //카카오는 그래들 라이브러리가 아니라서 다른 사이트들과 다르다.

    lateinit var binding : ActivityDailyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //키 해쉬값을 얻어오자
        var keyHash : String = Utility.getKeyHash(this)
        Log.i("key",keyHash)

        //MapView 객체 생성 및 ViewGroup에 붙이기
        var mapView : MapView = MapView(this)
        binding.containerMapview.addView(mapView) //렐러티브레이아웃에 mapView를 붙인다.
        //실디바이스에서만 보인다 나는 아직 안보임.


    }
}