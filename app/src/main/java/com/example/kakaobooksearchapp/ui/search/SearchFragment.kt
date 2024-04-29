package com.example.kakaobooksearchapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.BookmarkResult
import com.example.kakaobooksearchapp.HomeViewModel
import com.example.kakaobooksearchapp.adapter.SearchBookAdapter
import com.example.kakaobooksearchapp.databinding.FragmentSearchBinding
import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.room.BookSearchDatabase
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val homeViewModel by viewModels<HomeViewModel>(
        ownerProducer = { requireParentFragment() }
    )


    private val searchBookAdapter = SearchBookAdapter(
        onItemClick = {
            homeViewModel.routeBookInfo(it)
        },
        onBookmarkInsertClick = { item ->
            searchViewModel.addBookMark(item)
        },
        onBookmarkDeleteClick = { item ->
            searchViewModel.deleteBookMark(item)
        }
    )


    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this@SearchFragment.searchViewModel
        binding.rvSearchResult.adapter = searchBookAdapter

        searchViewModel.items.observe(viewLifecycleOwner) { items ->
            searchBookAdapter.submitList(items)
            Log.d("submit","재실행")
        }
        searchViewModel.bookMarkItems.observe(viewLifecycleOwner) { isBookmark ->

            when (isBookmark) {
                is BookmarkResult.AddBookmarkResult -> searchBookAdapter.addBookmark(isBookmark.item)
                is BookmarkResult.DeleteBookmarkResult ->searchBookAdapter.deleteBookmark(isBookmark.item)
            }
        }
    }
}