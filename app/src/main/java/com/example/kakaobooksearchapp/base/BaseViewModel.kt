package com.example.naverbooksearch.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel : ViewModel() {

    abstract val uiEvent: Flow<ViewEvent>
}

interface ViewState {
    object Idle : ViewState
}


interface ViewEvent {
    data class ShowToast(val message: String) : ViewEvent
}

