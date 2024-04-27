package com.example.kakaobooksearchapp.util

import android.webkit.WebView
import androidx.activity.OnBackPressedCallback

class WebViewOnBackPressedCallback(
    private val webView: WebView,
    private val onBackPress: () -> Unit
) :
    OnBackPressedCallback(webView.isEnabled) {
    override fun handleOnBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            onBackPress()
        }
    }
}