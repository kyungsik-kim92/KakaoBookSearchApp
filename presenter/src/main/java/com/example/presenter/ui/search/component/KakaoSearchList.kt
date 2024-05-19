package com.example.presenter.ui.search.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.KakaoBook

@Composable
fun KakaoSearchList(
    list: List<KakaoBook>,
    onClick: (KakaoBook) -> Unit,
    onDeleteBookmark: (KakaoBook) -> Unit,
    onInsertBookmark: (KakaoBook) -> Unit

) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { item ->
            KakaoSearchItem(
                item = item,
                onClick = onClick,
                onDeleteBookmark = onDeleteBookmark,
                onInsertBookmark = onInsertBookmark
            )
        }
    }
}