package com.athena.projectgroupwareapp.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.athena.projectgroupwareapp.BR

abstract class BaseActivity<T : ViewDataBinding, R : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var viewModel: R

    //초기화
    protected abstract fun getLayoutId(): Int
    protected abstract fun getViewModel(): Class<R>


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = DataBindingUtil.setContentView(this, getLayoutId()) //dataBinding
        viewModel = ViewModelProvider(this).get(getViewModel()) //initial viewModel

        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel) //뷰모델을 데이터 바인딩에 설정
    }
}