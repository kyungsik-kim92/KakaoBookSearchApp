package com.example.kakaobooksearchapp.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.adapter.BookmarkAdapter
import com.example.kakaobooksearchapp.databinding.FragmentBookmarkBinding
import com.example.kakaobooksearchapp.room.BookSearchDatabase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class BookmarkFragment() : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

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


    override fun onResume() {
        super.onResume()
        bookmarkViewModel.getFavoriteBooks()
    }


}