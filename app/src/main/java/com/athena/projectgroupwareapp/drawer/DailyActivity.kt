package com.athena.projectgroupwareapp.drawer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityDailyBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.kakao.util.maps.helper.Utility
import net.daum.mf.map.api.MapView


class DailyActivity : AppCompatActivity() {
    //카카오 맵을 추가하자
    //카카오는 그래들 라이브러리가 아니라서 다른 사이트들과 다르다.

    lateinit var binding : ActivityDailyBinding
    private val ACCESS_FINE_LOCATION = 1000

    //2. 현재 내 위치 정보 객체 (위도, 경도를 멤버로 보유한 객체)
    var myLocation : Location?= null //내 위치를 못 찾아 올수도 있으니까 null로 해준다
    //내 위치를 null로 주면 서울 시청을 나온다.

    //[ Google Fused Location API 사용 : play-services-location ]
    //위치는 아주 위험하다 퍼미션 (동적 퍼미션)
    //val providerClient : FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) } //사용자측에서 제공하는 위치
    //멤버변수에서는 context를 바로 쓰지 못한다. myLocation에 내 위치를 준다.




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //키 해쉬값을 얻어오자
        //var keyHash : String = Utility.getKeyHash(this)
        //Log.i("keyhash",keyHash)

        //MapView 객체 생성 및 ViewGroup에 붙이기
        //var mapView : MapView = MapView(this)
        // ViewGroup containerMapview=findViewById(R.id.container_mapview);
         //렐러티브레이아웃에 mapView를 붙인다.
        //실디바이스에서만 보인다
        //키 해시값을 조심해야한다. 노트북, 컴퓨터 자리마다 달라지므로 장소가 바뀌면 홈페이지에 변경해줘야함

        //Log.i("tag",mapView.toString())

        binding.attendance.setOnClickListener {

        }


    }//onCreate




}