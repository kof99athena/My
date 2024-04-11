package com.athena.projectgroupwareapp.main.tab2.chatting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.athena.projectgroupwareapp.databinding.ActivityChattingBinding
import com.athena.projectgroupwareapp.features.login.G
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.Date


//파이어베이스에 저장되는 document는 상대방이름이다.
//말 건 사람은 필드에서 볼수있다.
class ChattingActivity : AppCompatActivity() {
    lateinit var binding : ActivityChattingBinding
    lateinit var firebase : FirebaseFirestore //파이어스토어 : 많이 쓸 예정이니까 미리 프로퍼티를 설정하자

    lateinit var chatRef : CollectionReference //컬렉션 참조(→)하는 변수
    lateinit var chatRef2 : DocumentReference //컬렉션 참조(→)하는 변수
    //lateinit var chatRef3 : CollectionReference //컬렉션 참조(→)하는 변수

    lateinit var otherChatRef3 : CollectionReference //컬렉션 참조(→)하는 변수
    lateinit var otherChatRef4 : DocumentReference //컬렉션 참조(→)하는 변수
    //lateinit var otherChatRef6 : CollectionReference //컬렉션 참조(→)하는 변수

//    var otherId : String = GU.otherAccount?.id.toString()//상대방 사원번호와와
//    var myId : String = G.employeeAccount?.id.toString()//내 사원번호를 더해서 collection을 만들자
//    var collectionName : Int? = otherId.toInt()+myId.toInt()

    var chattingRoom : String = GU.otherAccount?.name.toString() //대화방이름, 다른사람 이름이다.

    //채팅방에 리사이클러뷰를 띄워보자
    lateinit var msgadapter : MessageAdapter
    var messageItems : MutableList<MessageItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)

        //캡쳐 불가하도록 만들자
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(binding.root)

        




        //채팅방 이름은 상대방 이름으로 표시하자
        binding.toolbarChat.setTitle(chattingRoom)


        //아답터 띄우기
        msgadapter = MessageAdapter(this,messageItems)
        binding.recyclerChatting.adapter = msgadapter

        //Log.i("recycler",msgadapter.toString())

        //chatting이라는 컬렉션을 만들자 - 회원번호를 더한 값으로 만들자.
        firebase = FirebaseFirestore.getInstance()
        reference() //레퍼런스 참조변수

        //기존데이터가져오기
        getChattingData()

        //내가 보낸 채팅 메세지를 저장한다.
        binding.btn.setOnClickListener{view->clickSend()}

    }//onCreate


    fun reference(){
        //1. 내 정보에 메세지를 저장하는 레퍼런스
        chatRef = firebase.collection("employeeList")
            .document(G.employeeAccount?.id.toString())
            .collection("chatting")
            .document(GU.otherAccount?.id.toString()) //상대방ID를 저장한다. 그래야 나중에 찾을수있다.
            .collection("message")//메세지에 내용을 등록한다. 밑에 도큐먼트 부분을 써줘야한다.

        Log.i("ahn777", G.employeeAccount?.id.toString())
        Log.i("ahn777",GU.otherAccount?.id.toString())

        //2. 메세지에 필드를 넣는다(안그러면 내용물이 없다 착각함._
        chatRef2 = firebase.collection("employeeList")
            .document(G.employeeAccount?.id.toString())
            .collection("chatting")
            .document(GU.otherAccount?.id.toString())

        //3. 상대방 정보에 메세지 저장하는 참레퍼런스
        otherChatRef3 = firebase.collection("employeeList")
            .document(GU.otherAccount?.id.toString())
            .collection("chatting")
            .document(G.employeeAccount?.id.toString())
            .collection("message")//상대방에게도 똑같이 저장한다. 밑에 도큐먼트 부분을 써줘야한다.

        //4. 메세지에 필드를 넣는다(안그러면 내용물이 없다 착각함._
        otherChatRef4 = firebase.collection("employeeList")
            .document(GU.otherAccount?.id.toString())
            .collection("chatting")
            .document(G.employeeAccount?.id.toString())
        //상대방 이름이 있어야 채팅방 이름에 넣을수있다.
    }

    //함수 1. 기존에 갖고있던 데이터 가져오기
    fun getChattingData(){
        //컬렉션에있는 내용을 가져오자
        chatRef.addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                //변경된 도큐먼트만 찾아달라고 요청하자
                val documentChanges : MutableList<DocumentChange> = value!!.documentChanges

                //처음시작할때! 기존에 채팅메세지가 10개있다고 한다면 10번 바뀐게 아니라는걸 말해줘야한다.
                //for each문 , java보다 더 이해하기 쉽다.
                for(documentChange in documentChanges){

                    //변경된 문서내역 중에서 데이터를 촬영한 SnapShot 얻어오기
                    var snapshot : DocumentSnapshot = documentChange.document

                    //도큐먼트에 있는 필드값 가져오기

                    //이제 Document에 있는 필드값 가져오기, 여기 조금 이상함
                    var msg : MutableMap<String, Any>? = snapshot.data

                    var name : String = msg?.get("name").toString()
                    var othername : String = msg?.get("othername").toString()

                    var otherId : String = msg?.get("otherId").toString()
                    var id : String = msg?.get("id").toString()

                    var message : String = msg?.get("message").toString()
                    var time : String = msg?.get("time").toString()

                    var profileUrl : String = msg?.get("imgUrl").toString()
                    var otherprofileUrl : String = msg?.get("otherprofileUrl").toString()

                    //var my : String = msg?.get("my").toString()
                    //var other : String = msg?.get("other").toString()

                    Log.i("ahn4747",otherId.toString())
                    Log.i("ahn4747",othername.toString())


                    var msg2 : MessageItem = MessageItem(name,id,message,profileUrl,time, othername,otherprofileUrl,otherId)
                    Log.i("test1111",msg2.toString())

                    //읽어온메세지를 리스트에 추가
                    messageItems.add(msg2)

                    //데이터 체인지 할때마다 부르자
                    msgadapter.notifyItemInserted(messageItems.size-1)

                    //리사이클러뷰의 스크롤을 아래로 위치시키자.
                    binding.recyclerChatting.scrollToPosition(messageItems.size-1)

                }//for문 완료
                //Toast.makeText(this@ChattingActivity, "" + messageItems.size, Toast.LENGTH_SHORT).show()
                Log.i("size",messageItems.size.toString())
            }
        })//addSnapshotListener
    }

    //함수 2. 샌드버튼 누를때 실행되는 함수
    fun clickSend(){


        //firebase에 저장할 나의 정보들
        var name : String = G.employeeAccount?.name.toString()
        var id : String = G.employeeAccount?.id.toString()
        var mymessage : String = binding.et.text.toString()
        var myimgUrl : String = G.employeeAccount?.imgProfile.toString()
        var othername : String = GU.otherAccount?.name.toString()
        var otherprofile : String = GU.otherAccount?.imgProfile.toString()
        var otherid : String = GU.otherAccount?.id.toString()

        //채팅방에 들어갈 시간 정보 만들기
//        var calendar : Calendar = Calendar.getInstance()
//        var time : String = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"

        val chatsdf = SimpleDateFormat("M월 d일 HH:mm")
        val chattoday = chatsdf.format(Date()).toString()

        //참조위치명이 중복되지 않도록 날짜를 이용
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val today = sdf.format(Date()).toString()
        Log.i("dateformat",today.toString())

        //필드값들을 HashMap에 만들지말고 객체로 만들어서 넣어버리자. MessageItem을 만들자
        var messageItem1 : MessageItem = MessageItem(name,id, mymessage,myimgUrl,chattoday,othername,otherprofile,otherid)
        var messageItem2 : MessageItem = MessageItem(name,id, mymessage,myimgUrl,chattoday,name,myimgUrl,id)

        //(var name : String, var id : String, var message : String, var imgUrl : String, var time : String, var othername : String, var otherprofileUrl : String)

        var myitem : MyItem = MyItem(GU.otherAccount?.name.toString())
        var otheritem : MyItem = MyItem(G.employeeAccount?.name.toString())


        chatRef.document(today).set(messageItem1) //내 메세지
        chatRef2.set(myitem) //info에 상대방 이름 넣기

        otherChatRef3.document(today).set(messageItem2) //상대방 메세지
        otherChatRef4.set(otheritem)

        binding.et.setText("")

        //소프트 키보드 내리기
        var inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

    }



}