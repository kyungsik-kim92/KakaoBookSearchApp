package com.example.presenter.ui.bookmark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presenter.ui.bookmark.component.KakaoBookmarkItem

@Composable
fun KakaoBookmarkSreen(
    viewModel: KakaoBookmarkViewModel = hiltViewModel(),

    ) {
    val bookmarkList = viewModel.bookmarkList.collectAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(bookmarkList) { item ->
            KakaoBookmarkItem(item = item)

        }
    }
}