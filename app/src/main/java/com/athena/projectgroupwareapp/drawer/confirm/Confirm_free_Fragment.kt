package com.athena.projectgroupwareapp.drawer.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.FragmentConfirmFreeBinding


class Confirm_free_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_free,container,false)
    }



}