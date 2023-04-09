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
    val providerClient : FusedLocationProviderClient by lazy { LocationServices.getFusedLocationProviderClient(this) } //사용자측에서 제공하는 위치
    //멤버변수에서는 context를 바로 쓰지 못한다. myLocation에 내 위치를 준다.




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //키 해쉬값을 얻어오자
        var keyHash : String = Utility.getKeyHash(this)
        Log.i("keyhash",keyHash)

        //MapView 객체 생성 및 ViewGroup에 붙이기
        var mapView : MapView = MapView(this)
        // ViewGroup containerMapview=findViewById(R.id.container_mapview);
         //렐러티브레이아웃에 mapView를 붙인다.
        //실디바이스에서만 보인다
        //키 해시값을 조심해야한다. 노트북, 컴퓨터 자리마다 달라지므로 장소가 바뀌면 홈페이지에 변경해줘야함

        //Log.i("tag",mapView.toString())

        //시작하면 내 위치 정보 제공에 대한 동적 퍼미션을 요청하자
        //FINE_LOCATION만 체크해도 코어스도 같이 한다. 그룹이라서!
        if(checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            //거부 -> 퍼미션 요청 대행사를 받아서 계약체결한다.
            permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)

        }else{
            //허용했으니까 바로 내위치 요청하면 된다.
            requestMyLocation()
        }

        binding.myLocation.setOnClickListener { requestMyLocation() }


    }//onCreate


    //퍼미션 요청 대행사 계약 및 등록
    val permissionLauncher : ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission(),object : ActivityResultCallback<Boolean>{
        override fun onActivityResult(result: Boolean?) {

            if(result!!) requestMyLocation() //결과가  true일때!
            else Toast.makeText(this@DailyActivity, "검색기능이 제한됩니다. ", Toast.LENGTH_SHORT).show()

        }

    })


    //내위치 요청 작업 메소드
    private fun requestMyLocation(){
        //위치정보 기준을 설정하는 요청 객체
        val request : com.google.android.gms.location.LocationRequest = com.google.android.gms.location.LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,1000).build()
        //위치정보는 얘가 가져옴 , 외우지말라고
        //PRIORITY_HIGH_ACCURACY  : GPS로 우선 적용해주세요

        //실시간 위치정보 갱신 요청 - 이 정보는 위치정보가 있을때만 쓸수있다. 동적허가 받앗는지 실행문이 써야한다. -> 그걸 onCreate메소드에서 썼으니까 이 지역에서는 못본다.
        //그걸 명시해줘야했다. addpermissioncheck를 자동으로 실행해줘야한다
        //providerClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        providerClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())


    }

    //위치검색결과 콜백객체
    private val locationCallback : LocationCallback= object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            myLocation = p0.lastLocation
            //정보 얻어왔으니까 실시간 업데이트 종료
            providerClient.removeLocationUpdates(this) //this는 메인이 아니다. locationCallback이다

        }
    }



}