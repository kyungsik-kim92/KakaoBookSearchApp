package com.example.kakaobooksearchapp.ui.search

import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.home.HomeViewModel
import com.example.kakaobooksearchapp.R
import com.example.kakaobooksearchapp.adapter.SearchBookAdapter
import com.example.kakaobooksearchapp.base.BaseFragment
import com.example.kakaobooksearchapp.base.ViewEvent
import com.example.kakaobooksearchapp.base.ViewState
import com.example.kakaobooksearchapp.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
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
            viewModel.addBookMark(item)
        },
        onBookmarkDeleteClick = { item ->
            viewModel.deleteBookMark(item)
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

            is SearchViewState.AddBookmarkResult -> {
                if (state.result) {
                    Snackbar.make(requireView(), "북마크에 추가 되었습니다.", Snackbar.LENGTH_SHORT).show()
                    searchBookAdapter.addBookmark(state.item)
                } else {
                    Snackbar.make(requireView(), "북마크에 추가를 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }

            }

            is SearchViewState.DeleteBookmarkResult -> {
                if (state.result) {
                    Snackbar.make(requireView(), "북마크에 해제 되었습니다.", Snackbar.LENGTH_SHORT).show()
                    searchBookAdapter.deleteBookmark(state.item)
                } else {
                    Snackbar.make(requireView(), "북마크에 해제를 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onChangeViewEvent(event: ViewEvent) {

    }


}