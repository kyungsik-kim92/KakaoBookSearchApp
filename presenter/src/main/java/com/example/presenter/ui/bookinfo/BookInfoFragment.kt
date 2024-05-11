package com.example.presenter.ui.bookinfo

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.mapper.toKakaoBookmark
import com.example.data.util.WebViewOnBackPressedCallback
import com.example.presenter.MainViewModel
import com.example.presenter.R
import com.example.presenter.base.BaseFragment
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.FragmentBookinfoBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookInfoFragment : BaseFragment<FragmentBookinfoBinding>(R.layout.fragment_bookinfo) {

    private val args by navArgs<BookInfoFragmentArgs>()
    override val viewModel by viewModels<BookInfoViewModel>()
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun initUi() {
        initWebView()
    }

    override fun onChangedViewState(state: ViewState) {}

    override fun onChangeViewEvent(event: ViewEvent) {
        when (event) {
            is BookInfoViewEvent.AddBookmark -> {
                mainViewModel.addBookmark(args.item.toKakaoBookmark())
            }

            is BookInfoViewEvent.DeleteBookmark -> {
                mainViewModel.deleteBookmark(args.item.toKakaoBookmark())
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        val item = args.item
        with(binding) {
            webview.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
            viewModel = this.viewModel
            bookItem = item

        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, WebViewOnBackPressedCallback(binding.webview, onBackPress = {
                findNavController().popBackStack()
            })
        )
    }


}