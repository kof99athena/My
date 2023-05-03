package com.athena.projectgroupwareapp.main.tab2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab2Binding
import com.athena.projectgroupwareapp.login.G
import com.athena.projectgroupwareapp.main.tab2.chatting.GU
import com.athena.projectgroupwareapp.main.tab2.recycler.MessageListItem
import com.athena.projectgroupwareapp.main.tab2.recycler.MsgListAdapter
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalAdapter
import com.athena.projectgroupwareapp.main.tab3.recycler.PersonnalItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class Tab2Fragment : Fragment() {

    lateinit var binding : FragmentTab2Binding

    lateinit var messageItem : MutableList<MessageListItem> //프래그먼트가 중복으로 나오므로 lateinit을 해준다.



//    var otherId : String = GU.otherAccount?.id.toString()//상대방 사원번호와와
//    var myId : String = G.employeeAccount?.id.toString()//내 사원번호를 더해서 collection을 만들자
//
//    var collectionName : Int = otherId.toInt()+myId.toInt()

    //var collectionname : Int = chatName.toInt()+chatName2.toInt()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTab2Binding.bind(view)

        messageItem = mutableListOf()

//        Log.i("collection",chatName)
//        Log.i("collection",chatName2)


        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        //채팅방 리스트에는 내가 대화했던 목록이 떠야한다.
        //토스트를 띄우는 습관을 들이기!
        firebase.collection("employeeList")
            .document(G.employeeAccount?.id.toString())
            .collection("chatting")
            .get().addOnSuccessListener {

               for(snapshot in it.documents) {
                   Log.i("test1111111", snapshot.reference.id.length.toString())
                   Log.i("test1111111", it.documents.size.toString())

                   snapshot.reference.collection("message")
                       .orderBy("time",Query.Direction.DESCENDING).limit(1)
                        .get().addOnSuccessListener {


                           for(snapshot in it.documents){
                               Log.i("test77777", it.documents.size.toString())
                               Log.i("test77777", it.documents.toString())

                               //var name: String = GU.otherAccount?.name.toString()
                               //var name: String = "기존대화"

                               var num : String = "new"
                               var name : String = snapshot.get("othername").toString()
                               var message: String = snapshot.get("message").toString()
                               var id: String = snapshot.get("id").toString() //내 아이디가 아니라 상대방
                               var otherId: String = snapshot.get("otherId").toString() //내 아이디가 아니라 상대방
                               var date: String = snapshot.get("time").toString() //내 시간이아니라 아니라 상대방
                               var profileUrl: String = snapshot.get("otherprofileUrl").toString()


                               messageItem.add(MessageListItem(name, message, date,id, num, profileUrl,otherId))

                               //메신저 내림차순으로 만들기 - sort : quick 정렬
                               messageItem.sortByDescending {
                                   it.date
                               }
                           }

                           Log.i("chatting time",messageItem.toString())
                           binding.recyclerMessage.adapter = MsgListAdapter(requireActivity(), messageItem)
                           binding.recyclerMessage.layoutManager =
                               LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                       }

               }


            }.addOnFailureListener {
                Toast.makeText(requireActivity(), "${it.message}", Toast.LENGTH_SHORT).show()
                Log.i("ahn1111","${it.message}")
            }

    }//onViewCreated


  }//Fragment
