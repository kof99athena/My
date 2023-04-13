package com.athena.projectgroupwareapp.main.tab3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.athena.projectgroupwareapp.databinding.ActivityIdCardBinding
import com.athena.projectgroupwareapp.login.EmployeeAccount
import com.athena.projectgroupwareapp.login.G
import com.athena.projectgroupwareapp.main.tab2.chatting.ChattingActivity
import com.athena.projectgroupwareapp.main.tab2.chatting.GU
import com.athena.projectgroupwareapp.main.tab2.chatting.OtherAccount
import com.bumptech.glide.Glide
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * 사원증
 */
class IdCardActivity : AppCompatActivity() {

    lateinit var binding: ActivityIdCardBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIdCardBinding.inflate(layoutInflater)
        setContentView(binding.root)




        var name: String? = intent.getStringExtra("name")
        var email: String? = intent.getStringExtra("email").toString()
        var tel: String? = intent.getStringExtra("tel") as String
        var imgId: String? = intent.getStringExtra("imgId")
        var id: String? = intent.getStringExtra("id") //상대방 id
        //여기서 말하는 "imgId"은 파이어베이스 필드가 아니라 아답터의 putExtra("imgId",item.imgId)의 name이다.

        Glide.with(this).load(imgId).into(binding.iv)
        binding.name.setText(name)
        binding.tel.setText(tel)
        binding.email.setText(email)
        binding.id.setText(id)


//        Log.i("email",email.toString())
//        Log.i("email",name.toString())
//        Log.i("email",imgId.toString())

        binding.btnMessage.setOnClickListener {

            //여기 조심
            var myId : String = G.employeeAccount?.id.toString()//내 사원번호를 더해서 collection을 만들자
            var collectionName : Int = id!!.toInt()+myId.toInt()
            Log.i("collectionname",collectionName.toString())

           var firebase : FirebaseFirestore //파이어스토어 : 많이 쓸 예정이니까 미리 프로퍼티를 설정하자
           //var chatRef : CollectionReference //컬렉션 참조(→)하는 변수
           firebase = FirebaseFirestore.getInstance()

            firebase.collection("employee").document("ManageHeadquarters").collection("accounting")
                .whereEqualTo("ID",G.employeeAccount?.id).get().addOnSuccessListener {

                    if(it.documents.size>0){
                        //사이즈가 1개 이상이라는것은 즉 null이 아니다.
                        var id : String = it.documents[0].id
                        var name : String = it.documents[0].get("name").toString()
                        var imgProfile : String = it.documents[0].get("profileUrl").toString()
                        var team : String = it.documents[0].get("team").toString()
                        var chatId : String = it.documents[0].get("chatId").toString()

                        G.employeeAccount = EmployeeAccount(id,name,imgProfile,team,chatId)
                        //로그인시 사원번호와 이름, 이미지Url을 G에 넣어준다. 나중에 메신저에서 가져와야함.
                }


            GU.otherAccount = OtherAccount(name.toString(), imgId.toString(), id.toString(), collectionName.toString())
            Log.i("info", name.toString() + imgId.toString() + id.toString())


            var intent: Intent = Intent(this, ChattingActivity::class.java)
            startActivity(intent)
        }


    }

}
    }