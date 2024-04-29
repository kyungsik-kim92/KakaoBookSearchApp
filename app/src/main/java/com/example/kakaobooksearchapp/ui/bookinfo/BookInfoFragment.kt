package com.example.kakaobooksearchapp.ui.bookinfo

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kakaobooksearchapp.BookmarkResult
import com.example.kakaobooksearchapp.MainViewModel
import com.example.kakaobooksearchapp.R
import com.example.kakaobooksearchapp.base.BaseFragment
import com.example.kakaobooksearchapp.base.ViewEvent
import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.databinding.FragmentBookinfoBinding
import com.example.kakaobooksearchapp.util.WebViewOnBackPressedCallback
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

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is BookInfoViewState.AddBookmarkResult -> {
                if (state.result) {
                    Snackbar.make(requireView(), "북마크에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()
                    mainViewModel.addBookmark(state.item)
                } else {
                    Snackbar.make(requireView(), "북마크에 추가를 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }
            }

            is BookInfoViewState.DeleteBookmarkResult -> {
                if (state.result) {
                    Snackbar.make(requireView(), "북마크에 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()
                    mainViewModel.deleteBookmark(state.item)
                } else {
                    Snackbar.make(requireView(), "북마크에 해제를 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onChangeViewEvent(event: ViewEvent) {

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