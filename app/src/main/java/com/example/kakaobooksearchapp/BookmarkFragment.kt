package com.example.kakaobooksearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.adapter.BookmarkAdapter
import com.example.kakaobooksearchapp.databinding.FragmentBookmarkBinding
import com.example.kakaobooksearchapp.room.BookSearchDatabase

class BookmarkFragment() : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding

    private val bookmarkViewModel by viewModels<BookmarkViewModel> {
        val db = BookSearchDatabase.getInstance(requireContext())
        val bookmarkRepository = BookmarkRepository(db.bookSearchDao())
        BookmarkViewModel.provideFactory(bookmarkRepository)
    }
    private val bookmarkAdapter = BookmarkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavoriteBooks.adapter = bookmarkAdapter
        bookmarkViewModel.items.observe(viewLifecycleOwner) { items ->
            bookmarkAdapter.submitList(items)
        }
    }


}