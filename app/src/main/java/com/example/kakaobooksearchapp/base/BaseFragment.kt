package com.example.naverbooksearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    abstract val viewModel: BaseViewModel

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initViewModel()
    }

    abstract fun initUi()

    private fun initViewModel() {
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            viewModel.uiEvent.collect(::onChangedUiEvent)
        }
    }
    abstract fun onChangedUiEvent(viewEvent: ViewEvent)
}