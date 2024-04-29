package com.example.kakaobooksearchapp

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaobooksearchapp.network.response.KakaoBookItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

//    val routeBookItem = MutableLiveData<KakaoBookItem>()
//    fun routeBookInfo(item: KakaoBookItem) {
//        viewModelScope.launch(IO) {
//            routeBookItem.postValue(item)
//
//        }
//    }

    private val _viewStateLiveData = MutableLiveData<HomeViewState>()
    val viewStateLiveData: LiveData<HomeViewState> = _viewStateLiveData
fun onChangedViewState(viewState: HomeViewState) {
    viewModelScope.launch {
        _viewStateLiveData.value = viewState

    }
}

    fun routeBookInfo(item: KakaoBookItem) {
        onChangedViewState(HomeViewState.RouteBookInfo(item))
    }


}




sealed class HomeViewState {
    data class RouteBookInfo(val item: KakaoBookItem) : HomeViewState()

}
