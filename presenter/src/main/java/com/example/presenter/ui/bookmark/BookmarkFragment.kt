package com.example.presenter.ui.bookmark

import androidx.fragment.app.viewModels
import com.example.presenter.R
import com.example.presenter.adapter.BookmarkAdapter
import com.example.presenter.base.BaseFragment
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment() : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

    private val bookmarkAdapter = BookmarkAdapter()

    override val viewModel by viewModels<BookmarkViewModel>()


    override fun initUi() {
        binding.rvFavoriteBooks.adapter = bookmarkAdapter
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
    }

    override fun onChangedViewState(state: ViewState) {
    }

    override fun onChangeViewEvent(event: ViewEvent) {
        when (event) {
            is BookmarkViewEvent.BookmarkResult -> {
                bookmarkAdapter.submitList(event.list)
            }
        }
    }


}