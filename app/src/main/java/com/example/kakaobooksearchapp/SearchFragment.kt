package com.example.kakaobooksearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kakaobooksearchapp.adapter.SearchBookAdapter
import com.example.kakaobooksearchapp.databinding.FragmentSearchBinding
import com.example.kakaobooksearchapp.network.BookApiService

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchBookAdapter = SearchBookAdapter()

    private val viewModel by viewModels<SearchViewModel>{
        val repository = SearchRepository(BookApiService.create())
        SearchViewModel.provideFactory(repository)
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
        viewModel.items.observe(viewLifecycleOwner){items ->
            searchBookAdapter.submitList(items)
        }

    }

}