package com.athena.projectgroupwareapp.main.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab2Binding

class Tab2Fragment : Fragment() {

    lateinit var binding : FragmentTab2Binding

    var messageItem : MutableList<MessageItem> = mutableListOf()

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

        messageItem.add(MessageItem("안혜영","개발팀인데요","2024/04/01","3",R.drawable.pro1))
        messageItem.add(MessageItem("김민수","넵","2024/04/01","1",R.drawable.pro2))
        messageItem.add(MessageItem("이지영, 김지수, 우영우","아니요 그거 못들엇어요","2024/04/01","1",R.drawable.pro10))
        messageItem.add(MessageItem("Kim junsoo","즐퇴하세요","2024/04/01","2",R.drawable.pro4))
        messageItem.add(MessageItem("한준희","ㅋㅋㅋㅋㅋㅋㅋㅋ","2024/04/01","3",R.drawable.pro5))
        messageItem.add(MessageItem("최주영","개발팀인데요","2024/04/01","2",R.drawable.pro6))

        binding.recyclerMessage.adapter = MsgAdapter(requireActivity(),messageItem)
        binding.recyclerMessage.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

    }


}