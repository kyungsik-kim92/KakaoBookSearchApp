package com.example.presenter.ui.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.KakaoBook
import com.example.presenter.ui.search.SearchViewModel

@Composable
fun KakaoSearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onRouteDetail: (KakaoBook) -> Unit,
    onInsertBookmark: (KakaoBook) -> Unit,
    onDeleteBookmark: (KakaoBook) -> Unit
) {
    val searchList = viewModel.searchViewState.collectAsState().value.list

    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(
            onSearch = viewModel::searchBooks
        )

        KakaoSearchList(
            list = searchList,
            onClick = { item ->
                onRouteDetail(item)
            },
            onDeleteBookmark = onDeleteBookmark,
            onInsertBookmark = onInsertBookmark
        )
    }
}