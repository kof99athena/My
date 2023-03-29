package com.athena.projectgroupwareapp.main.tab1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentTab1Binding
import com.athena.projectgroupwareapp.databinding.FragmentTab3Binding
import com.google.android.material.bottomappbar.BottomAppBar

class Tab1Fragment : Fragment() {

    lateinit var binding : FragmentTab1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTab1Binding.bind(view)


        binding.viewTotal.setOnClickListener{
            startActivity(Intent(activity,TotalviewActivity::class.java))
        }

        binding.viewMyteam.setOnClickListener {
            startActivity(Intent(activity,MyteamActivity::class.java))
        }

    }

}