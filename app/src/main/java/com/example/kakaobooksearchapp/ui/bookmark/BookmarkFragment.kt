package com.example.kakaobooksearchapp.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.R
import com.example.kakaobooksearchapp.adapter.BookmarkAdapter
import com.example.kakaobooksearchapp.base.BaseFragment
import com.example.kakaobooksearchapp.base.BaseViewModel
import com.example.kakaobooksearchapp.base.ViewEvent
import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment() : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

    private val bookmarkAdapter = BookmarkAdapter()

    override val viewModel by viewModels<BookmarkViewModel>()


    override fun initUi() {
        binding.rvFavoriteBooks.adapter = bookmarkAdapter
    }

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is BookmarkViewState.BookmarkResult -> {
                bookmarkAdapter.submitList(state.list)
            }
        }
    }

    override fun onChangeViewEvent(event: ViewEvent) {

    }


    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteBooks()
    }


}