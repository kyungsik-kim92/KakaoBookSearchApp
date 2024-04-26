package com.example.kakaobooksearchapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    abstract val viewModel: BaseViewModel

    protected lateinit var binding: B


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
        initUi()
        initViewModel()
    }

    abstract fun initUi()

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.uiEvent.collect(::onChangedUiState)
        }
    }

    abstract fun onChangedUiState(viewState: ViewEvent)

}