package com.example.presenter.ui.search

import androidx.fragment.app.viewModels
import com.example.presenter.R
import com.example.presenter.adapter.SearchBookAdapter
import com.example.presenter.base.BaseFragment
import com.example.presenter.base.ViewEvent
import com.example.presenter.base.ViewState
import com.example.presenter.databinding.FragmentSearchBinding
import com.example.presenter.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {


    override val viewModel by viewModels<SearchViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>(
        ownerProducer = { requireParentFragment() }
    )


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


    }

    override fun onChangedViewState(state: ViewState) {
        when (state) {
            is SearchViewState.GetSearchResult -> {
                searchBookAdapter.submitList(state.list)
            }

        }
    }


    override fun onChangeViewEvent(event: ViewEvent) {

    }


}