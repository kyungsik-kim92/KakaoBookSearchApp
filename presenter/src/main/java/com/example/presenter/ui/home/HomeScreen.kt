package com.example.presenter.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.domain.model.KakaoBook
import com.example.presenter.ui.home.component.HomeTab
import com.example.presenter.ui.home.component.HomeTabContents

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onRouteDetail: (KakaoBook) -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { 2 })

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (tabs, contents) = createRefs()

        HomeTab(
            pagerState = pagerState,
            modifier = Modifier.constrainAs(tabs) {
                linkTo(start = parent.start, end = parent.end)
                linkTo(top = parent.top, bottom = parent.bottom, bias = 1f)
                width = Dimension.fillToConstraints
                height = Dimension.value(70.dp)
            }
        )

        HomeTabContents(pagerState = pagerState,
            modifier = Modifier.constrainAs(contents) {
                linkTo(start = parent.start, end = parent.end)
                linkTo(top = parent.top, bottom = tabs.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            onRouteDetail = onRouteDetail,
        )
    }
}