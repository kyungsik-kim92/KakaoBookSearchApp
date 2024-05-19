package com.example.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.KakaoBook
import com.example.presenter.util.NavigationUtil
import com.example.presenter.util.Screen


@Composable
fun rememberMainNavigator(
    navHostController: NavHostController = rememberNavController()
): MainNavigator = remember(navHostController) {
    MainNavigatorImpl(navHostController)
}


@Stable
class MainNavigatorImpl(
    override val navController: NavHostController
) : MainNavigator {
    override val startDestination: String
        get() = Screen.Home.route

    override fun routeDetail(item: KakaoBook) {
        navController.navigate(
            Screen.Detail(NavigationUtil.passItem(item)).sendRoute
        )
    }
}


interface MainNavigator {

    val startDestination: String

    val navController: NavHostController

    fun routeDetail(item: KakaoBook)

}

