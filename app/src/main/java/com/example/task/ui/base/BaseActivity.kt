package com.example.task.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewDataBindingKtx
import com.example.task.utils.ConnectionLiveData

abstract class BaseActivity<B: Any, V: Any>: AppCompatActivity() {

    lateinit var binding: B
    lateinit var viewModel: V

    protected val connectionLiveData by lazy { ConnectionLiveData(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = configureBinding()
        prepareBinding()
        viewModel = configureViewModel()
        subscribeEvents()
    }

    private fun prepareBinding(){
        (binding as ViewDataBinding).lifecycleOwner = this
        setContentView((binding as ViewDataBinding).root)
    }

    abstract fun configureBinding(): B
    abstract fun configureViewModel(): V
    abstract fun subscribeEvents()
}