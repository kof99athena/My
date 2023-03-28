package com.athena.projectgroupwareapp.drawer

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityConfirmBinding
import com.athena.projectgroupwareapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_confirm)
        binding = ActivityConfirmBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var til = binding.textInput
        var s = til.editText?.text.toString()

        var acTv = binding.confirmSelect
        var sort : Array<String> = resources.getStringArray(R.array.sort)
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,sort)
        acTv.setAdapter(adapter)



    }
}