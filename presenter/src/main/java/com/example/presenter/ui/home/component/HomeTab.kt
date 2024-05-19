package com.example.presenter.ui.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.domain.model.KakaoBook
import com.example.presenter.R
import com.example.presenter.ui.bookmark.KakaoBookmarkSreen
import com.example.presenter.ui.search.component.KakaoSearchScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTab(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {

    val scope = rememberCoroutineScope()

    val tabTextList = stringArrayResource(id = R.array.array_main_tab_text)
    val tabIconList = listOf(
        painterResource(id = R.drawable.baseline_search_24),
        painterResource(id = R.drawable.baseline_favorite_24)
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp / 2

    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        indicator = {},
        divider = {}
    ) {
        tabTextList.zip(tabIconList).forEachIndexed { position, item ->
            Tab(
                text = { Text(text = item.first) },
                selected = pagerState.currentPage == position,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(position)
                    }
                },
                icon = {
                    Image(painter = item.second, contentDescription = "")
                },
                modifier = Modifier.width(screenWidth.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabContents(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onRouteDetail: (KakaoBook) -> Unit,
) {

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        userScrollEnabled = false,
        beyondBoundsPageCount = pagerState.pageCount
    ) { index ->

        when (index) {
            0 -> {
                KakaoSearchScreen(onRouteDetail = onRouteDetail)
            }

            1 -> {
                KakaoBookmarkSreen()
            }
        }
    }
}