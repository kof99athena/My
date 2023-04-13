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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentTab2Binding.bind(view)

        messageItem = mutableListOf()

//        Log.i("collection",chatName)
//        Log.i("collection",chatName2)


        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        //채팅방 리스트에는 내가 대화했던 목록이 떠야한다.
        //토스트를 띄우는 습관을 들이기!

        firebase.collectionGroup("employeeList")
            .whereEqualTo("id",G.employeeAccount?.id)
            .get().addOnSuccessListener {


                var name: String = GU.otherAccount?.name.toString()
                var num : String = it.documents.size.toString()

                for (snapshot in it.documents) {

                    //var name : String = snapshot.get("name").toString()
                    var message: String = snapshot.get("message").toString()
                    var date: String = snapshot.get("time").toString()
                    var profileUrl: String = snapshot.get("imgUrl").toString()

                    messageItem.add(MessageListItem(name, message, date, num, profileUrl))

                }

                binding.recyclerMessage.adapter = MsgListAdapter(requireActivity(), messageItem)
                binding.recyclerMessage.layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)


            }.addOnFailureListener {
                Toast.makeText(requireActivity(), "${it.message}", Toast.LENGTH_SHORT).show()
                Log.i("ahn1111","${it.message}")
            }


//        firebase.collectionGroup("employeeList")
//            .whereEqualTo("id",G.employeeAccount?.id)
//            .whereEqualTo(collectionName.toString(),collectionName)
//            .get().addOnSuccessListener {
//            Toast.makeText(requireActivity(), "${it.documents.size}", Toast.LENGTH_SHORT).show()
//
//            var name : String = GU.otherAccount?.name.toString()
//
//            for (snapshot in it.documents){
//
//              //var name : String = snapshot.get("name").toString()
//                var message : String = snapshot.get("message").toString()
//                var date : String = snapshot.get("time").toString()
//                var num : String = snapshot.get("num").toString() ?: ""
//                var profileUrl : String = snapshot.get("imgUrl").toString()
//
//                messageItem.add(MessageListItem(name,message,date,num,profileUrl))
//
//            }
//
//            binding.recyclerMessage.adapter = MsgListAdapter(requireActivity(),messageItem)
//            binding.recyclerMessage.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
//
//
//        }.addOnFailureListener {
//            Toast.makeText(requireActivity(), "${it.message}", Toast.LENGTH_SHORT).show()
//            Log.i("ahn1111","${it.message}")
//        }


    }//onViewCreated


  }//Fragment
