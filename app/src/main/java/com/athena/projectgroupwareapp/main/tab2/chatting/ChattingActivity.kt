package com.athena.projectgroupwareapp.main.tab2.chatting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.athena.projectgroupwareapp.databinding.ActivityChattingBinding
import com.athena.projectgroupwareapp.login.G
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Objects


//파이어베이스에 저장되는 document는 상대방이름이다.
//말 건 사람은 필드에서 볼수있다.
class ChattingActivity : AppCompatActivity() {
    lateinit var binding : ActivityChattingBinding
    lateinit var firebase : FirebaseFirestore //파이어스토어 : 많이 쓸 예정이니까 미리 프로퍼티를 설정하자
    lateinit var chatRef : CollectionReference //컬렉션 참조(→)하는 변수
    var chatName : String = GU.otherAccount?.name.toString()


    //채팅방에 리사이클러뷰를 띄워보자
    lateinit var msgadapter : MessageAdapter
    var messageItems : MutableList<MessageItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //채팅방 이름은 상대방 이름으로 표시하자
        binding.toolbarChat.setTitle(chatName)
        Log.i("chatname",chatName)

        //아답터 띄우기
        msgadapter = MessageAdapter(this,messageItems)
        binding.recyclerChatting.adapter = msgadapter

        //Log.i("recycler",msgadapter.toString())

        //chatting이라는 컬렉션을 만들자
        firebase = FirebaseFirestore.getInstance()
        chatRef = firebase.collection(chatName)

        //컬렉션에있는 내용을 가져오자
        chatRef.addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                //변경된 도큐먼트만 찾아달라고 요청하자
                val documentChanges : MutableList<DocumentChange> = value!!.documentChanges

                //처음시작할때! 기존에 채팅메세지가 10개있다고 한다면 10번 바뀐게 아니라는걸 말해줘야한다.
                for(documentChange : DocumentChange in documentChanges){

                    //변경된 문서내역 중에서 데이터를 촬영한 SnapShot 얻어오기
                    var snapshot : DocumentSnapshot = documentChange.document

                    //도큐먼트에 있는 필드값 가져오기

                    //이제 Document에 있는 필드값 가져오기, 여기 조금 이상함
                    var msg : MutableMap<String, Any>? = snapshot.data

                    var name : String = msg?.get("name").toString()
                    var message : String = msg?.get("message").toString()
                    var time : String = msg?.get("time").toString()
                    var profileUrl : String = msg?.get("imgUrl").toString()

                    Log.i("total",name+message+time+profileUrl)


                    //읽어온메세지를 리스트에 추가
                    messageItems.add(MessageItem(name,message,profileUrl,time))

                    //데이터 체인지 할때마다 부르자
                    msgadapter.notifyItemInserted(messageItems.size-1)

                    //리사이클러뷰의 스크롤을 아래로 위치시키자.
                    binding.recyclerChatting.scrollToPosition(messageItems.size-1)

                }//for문 완료
                //Toast.makeText(this@ChattingActivity, "" + messageItems.size, Toast.LENGTH_SHORT).show()
                Log.i("size",messageItems.size.toString())
            }

        })//addSnapshotListener

        //내가 보낸 채팅 메세지를 저장한다.
        binding.btn.setOnClickListener{view->clickSend()}

    }//onCreate

    fun clickSend(){

        //firebase에 저장할 나의 정보들
        var name : String = G.employeeAccount?.name.toString()
        var message : String = binding.et.text.toString()
        var imgUrl : String = G.employeeAccount?.imgProfile.toString()
        //Log.i("myaccount",name+message+imgUrl)

        //채팅방에 들어갈 시간 정보 만들기
        var calendar : Calendar = Calendar.getInstance()
        var time : String = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
        //Log.i("myaccount",time)

        //필드값들을 HashMap에 만들지말고 객체로 만들어서 넣어버리자. MessageItem을 만들자
        var messageItem : MessageItem = MessageItem(name, message, imgUrl, time)

        //컬렉션에 채팅 메세지 저장하기 - 도큐먼트는 상대방 이름으로 정하자

        //참조위치명이 중복되지 않도록 날짜를 이용
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val today = sdf.format(Date())

        chatRef.document(chatName+" "+today).set(messageItem)
        binding.et.setText("")

        //소프트 키보드 내리기
        var inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

    }



}