package com.example.kakaobooksearchapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _items = MutableLiveData<List<KakaoBookItem>>()
    val items: LiveData<List<KakaoBookItem>> = _items
    val inputSearchLiveData = MutableLiveData("")

    fun searchBooks() = viewModelScope.launch {
        inputSearchLiveData.value?.let { input ->

            val response = searchRepository.searchBooks(
                input,
                DEFAULT_SEARCH_SORT,
                DEFAULT_SEARCH_PAGE,
                DEFAULT_SEARCH_SIZE
            )
            if (response.isSuccessful) {
                _items.value = response.body()?.kakaoBookItems
            }
        }
    }

    companion object {

        private const val DEFAULT_SEARCH_SORT = "accuracy"
        private const val DEFAULT_SEARCH_PAGE = 1
        private const val DEFAULT_SEARCH_SIZE = 30


        fun provideFactory(repository: SearchRepository) = viewModelFactory {
            initializer {
                SearchViewModel(repository)
            }
        }

    }


}