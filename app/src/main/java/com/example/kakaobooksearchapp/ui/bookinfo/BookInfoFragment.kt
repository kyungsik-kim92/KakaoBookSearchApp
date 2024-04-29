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
import com.example.kakaobooksearchapp.room.BookSearchDatabase
import com.example.kakaobooksearchapp.ui.bookinfo.BookInfoViewModel.BookmarkViewState
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository
import com.example.kakaobooksearchapp.util.BookInfoViewModelFactory
import com.example.kakaobooksearchapp.util.WebViewOnBackPressedCallback
import com.google.android.material.snackbar.Snackbar

class BookInfoFragment : Fragment() {
    private lateinit var binding: FragmentBookinfoBinding


    private val bookInfoViewModel: BookInfoViewModel by viewModels(

        factoryProducer = {
            val db = BookSearchDatabase.getInstance(requireContext())
            val bookmarkRepository = BookmarkRepository(db.bookSearchDao())
            BookInfoViewModelFactory(bookmarkRepository)
        }
    )




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
        initWebView()
        binding.viewModel = this.bookInfoViewModel


        bookInfoViewModel.bookMarkItems.observe(viewLifecycleOwner) { isBookmark ->
            when (isBookmark) {
                is BookmarkResult.AddBookmarkResult -> {
                    if (isBookmark.result) {
                        Snackbar.make(requireView(), "북마크에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()
                        BookmarkViewState.AddBookmark(isBookmark.item)
                    } else {
                        Snackbar.make(requireView(), "북마크에 추가를 실패하였습니다.", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }

                is BookmarkResult.DeleteBookmarkResult -> {
                    if (isBookmark.result) {
                        Snackbar.make(requireView(), "북마크에 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()
                        BookmarkViewState.DeleteBookmark(isBookmark.item)
                    } else {
                        Snackbar.make(requireView(), "북마크에 해제를 실패하였습니다.", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
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