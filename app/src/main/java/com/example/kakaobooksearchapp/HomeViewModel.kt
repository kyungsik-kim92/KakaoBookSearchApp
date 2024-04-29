package com.example.kakaobooksearchapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    val routeBookItem = MutableLiveData<KakaoBookItem>()
    fun routeBookInfo(item: KakaoBookItem) {
        viewModelScope.launch(IO) {
            routeBookItem.postValue(item)

        }
    }

}

