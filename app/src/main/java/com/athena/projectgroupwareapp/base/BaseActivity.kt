package com.athena.projectgroupwareapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.athena.projectgroupwareapp.BR

abstract class BaseActivity<T : ViewDataBinding, R : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var viewModel: R

    //initialize
    protected abstract fun getLayoutId(): Int
    protected abstract fun getViewModel(): Class<R>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutId()) //dataBinding
        viewModel = ViewModelProvider(this).get(getViewModel()) //initial viewModel

        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel) //setting viewModel - dataBinding
    }
}