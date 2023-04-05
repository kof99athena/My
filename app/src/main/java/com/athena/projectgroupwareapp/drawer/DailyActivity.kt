package com.athena.projectgroupwareapp.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityDailyBinding
import com.kakao.util.maps.helper.Utility
import net.daum.mf.map.api.MapView


class DailyActivity : AppCompatActivity() {
    //카카오 맵을 추가하자
    //카카오는 그래들 라이브러리가 아니라서 다른 사이트들과 다르다.

    lateinit var binding : ActivityDailyBinding
    private val ACCESS_FINE_LOCATION = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //키 해쉬값을 얻어오자
        var keyHash : String = Utility.getKeyHash(this)
        Log.i("key",keyHash)

        //MapView 객체 생성 및 ViewGroup에 붙이기
        var mapView : MapView = MapView(this)
        // ViewGroup containerMapview=findViewById(R.id.container_mapview);
        binding.containerMapview.addView(mapView) //렐러티브레이아웃에 mapView를 붙인다.
        //실디바이스에서만 보인다
        //키 해시값을 조심해야한다. 노트북, 컴퓨터 자리마다 달라지므로 장소가 바뀌면 홈페이지에 변경해줘야함

        Log.i("tag",mapView.toString())




    }
}