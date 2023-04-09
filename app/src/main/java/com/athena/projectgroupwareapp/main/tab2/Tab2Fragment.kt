package com.athena.projectgroupwareapp.main.tab2

import android.os.Bundle
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
import com.google.firebase.firestore.FirebaseFirestore

class Tab2Fragment : Fragment() {

    lateinit var binding : FragmentTab2Binding

    var messageItem : MutableList<MessageListItem> = mutableListOf()
    var chatName : String = G.employeeAccount?.name.toString() //내이름으로 된 컬렉션이 있으면 가져온다. 왜냐면 그건 다른사람이 나한테 메세지를 건거니까.

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


        var firebase : FirebaseFirestore = FirebaseFirestore.getInstance()
        firebase.collection(chatName).get().addOnSuccessListener {
            for (snapshot in it.documents){
                var name : String = snapshot.get("name").toString()
                var message : String = snapshot.get("message").toString()
                var date : String = snapshot.get("time").toString()
                var num : String = snapshot.get("num").toString() ?: ""
                var profileUrl : String = snapshot.get("imgUrl").toString()

                messageItem.add(MessageListItem(name,message,date,num,profileUrl))
            }

            binding.recyclerMessage.adapter = MsgListAdapter(requireActivity(),messageItem)
            binding.recyclerMessage.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        }//addOnSuccessListener

    }//onViewCreated


  }//Fragment
