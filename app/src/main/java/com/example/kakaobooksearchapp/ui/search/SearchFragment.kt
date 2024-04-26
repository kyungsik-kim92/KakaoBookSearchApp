package com.example.kakaobooksearchapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.ui.bookmark.BookmarkRepository
import com.example.kakaobooksearchapp.adapter.SearchBookAdapter
import com.example.kakaobooksearchapp.databinding.FragmentSearchBinding
import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.room.BookSearchDatabase

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding


    private val searchBookAdapter = SearchBookAdapter(
        onBookmarkInsertClick = { item ->
            viewModel.addBookMark(item)
        },
        onBookmarkDeleteClick = { item ->
            viewModel.deleteBookMark(item)
        }
    )

    private val viewModel by viewModels<SearchViewModel> {
        val db = BookSearchDatabase.getInstance(requireContext())
        val searchRepository = SearchRepository(BookApiService.create())
        val bookmarkRepository = BookmarkRepository(db.bookSearchDao())
        SearchViewModel.provideFactory(searchRepository, bookmarkRepository)
    }

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
        binding.viewModel = this.viewModel
        binding.rvSearchResult.adapter = searchBookAdapter

        viewModel.items.observe(viewLifecycleOwner) { items ->
            searchBookAdapter.submitList(items)
        }
        viewModel.bookmarkItems.observe(viewLifecycleOwner) { bookmarkItems ->
            searchBookAdapter.addBookmark(bookmarkItems)

        }
    }
}