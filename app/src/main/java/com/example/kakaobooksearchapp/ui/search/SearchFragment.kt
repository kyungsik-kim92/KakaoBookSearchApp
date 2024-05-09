package com.example.kakaobooksearchapp.ui.search

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.R
import com.example.kakaobooksearchapp.adapter.SearchBookAdapter
import com.example.kakaobooksearchapp.base.BaseFragment
import com.example.kakaobooksearchapp.base.ViewEvent
import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.databinding.FragmentSearchBinding
import com.example.kakaobooksearchapp.home.HomeViewModel
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