package com.example.kakaobooksearchapp.ui.bookinfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kakaobooksearchapp.BookmarkResult
import com.example.kakaobooksearchapp.databinding.FragmentBookinfoBinding
import com.example.kakaobooksearchapp.util.WebViewOnBackPressedCallback
import com.google.android.material.snackbar.Snackbar
import com.example.kakaobooksearchapp.ui.bookinfo.BookInfoViewModel.BookmarkViewState

class BookInfoFragment : Fragment() {
    private lateinit var binding: FragmentBookinfoBinding
    private val bookInfoViewModel by viewModels<BookInfoViewModel>()
    private val args by navArgs<BookInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookinfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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