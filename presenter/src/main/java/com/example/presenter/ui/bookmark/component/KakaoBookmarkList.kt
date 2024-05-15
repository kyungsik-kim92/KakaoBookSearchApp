package com.example.presenter.ui.bookmark.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.KakaoBookmark

@Composable
fun KakaoBookmarkList(
    list: List<KakaoBookmark>
){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
       items(list){item ->
            KakaoBookmarkItem(item = item)

        }
    }
}