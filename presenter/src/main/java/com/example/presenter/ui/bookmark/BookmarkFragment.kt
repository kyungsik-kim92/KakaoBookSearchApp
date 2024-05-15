package com.example.presenter.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presenter.R
import com.example.presenter.databinding.FragmentBookmarkBinding
import com.example.presenter.ui.bookmark.component.KakaoBookmarkList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment() : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private val viewModel by viewModels<BookmarkViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteBooks.setContent {
            KakaoBookmarkList(viewModel.bookmarkList.collectAsState().value)
        }
    }
}