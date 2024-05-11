package com.example.presenter.ui.search

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.toKakaoBook
import com.example.presenter.MainViewEvent
import com.example.presenter.MainViewModel
import com.example.presenter.R
import com.example.presenter.adapter.SearchBookAdapter
import com.example.presenter.base.BaseFragment
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.FragmentSearchBinding
import com.example.presenter.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {


    override val viewModel by viewModels<SearchViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>(
        ownerProducer = { requireParentFragment() }
    )
    private val mainViewModel by activityViewModels<MainViewModel>()


    private val searchBookAdapter = SearchBookAdapter(
        onItemClick = {
            homeViewModel.routeBookInfo(it)
        },
        onBookmarkInsertClick = { item ->
            viewModel.addBookmark(item)
        },
        onBookmarkDeleteClick = { item ->
            viewModel.deleteBookmark(item)
        }
    )


    override fun initUi() {
        binding.rvSearchResult.adapter = searchBookAdapter
        mainViewModel.viewEvent.map(::onChangeViewEvent).launchIn(requireActivity().lifecycleScope)

    }

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is SearchViewState.GetSearchResult -> {
                searchBookAdapter.submitList(state.list)
            }

        }
    }


    override fun onChangeViewEvent(event: ViewEvent) {
        when (event) {
            is SearchViewEvent.DeleteBookItem -> {
                searchBookAdapter.deleteBookmark(event.item)
            }

            is SearchViewEvent.AddBookItem -> {
                searchBookAdapter.addBookmark(event.item)
            }

            is MainViewEvent.AddBookmark -> {
                searchBookAdapter.addBookmark(event.item.toKakaoBook())
            }

            is MainViewEvent.DeleteBookmark -> {
                searchBookAdapter.deleteBookmark(event.item.toKakaoBook())
            }
        }
    }

}