package com.example.presenter

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.model.KakaoBook
import com.example.presenter.ui.bookinfo.BookInfoScreen
import com.example.presenter.ui.home.HomeScreen
import com.example.presenter.util.NavigationUtil
import com.example.presenter.util.Screen

@Composable
fun MainScreen() {
    val mainNavigator = rememberMainNavigator()

    NavHost(
        navController = mainNavigator.navController,
        startDestination = mainNavigator.startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onRouteDetail = mainNavigator::routeDetail)
        }

        composable(
            route = Screen.Detail().route,
            arguments = listOf(
                navArgument(Screen.Detail().passKakaoBookItem) {
                    type = NavigationUtil.assetParamTypeOf<KakaoBook>()
                }
            )
        ) {
            BookInfoScreen()
        }
    }
}