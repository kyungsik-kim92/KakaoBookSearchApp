package com.example.presenter.ui.bookinfo

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presenter.R


@Composable
fun BookInfoScreen(
    viewModel: BookInfoViewModel = hiltViewModel()
) {

    val item = viewModel.bookInfoState.collectAsState().value.item


    item?.let {

        val bookmarkImage = if (item.isBookmark) {
            painterResource(id = R.drawable.baseline_clicked_star_24)
        } else {
            painterResource(id = R.drawable.baseline_empty_star_border_24)
        }


        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.setSupportZoom(true)
                    }
                },
                update = { webView ->
                    webView.loadUrl(item.url)
                },
                modifier = Modifier.fillMaxSize()
            )

            Image(
                painter = bookmarkImage,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 10.dp, end = 10.dp)
                    .align(Alignment.BottomEnd)
                    .clickable { viewModel.toggleBookmark() }
            )
        }

    }


}