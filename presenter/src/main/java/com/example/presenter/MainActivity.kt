package com.example.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.model.KakaoBook
import com.example.presenter.ui.bookinfo.BookInfoScreen
import com.example.presenter.ui.home.HomeScreen
import com.example.presenter.util.NavigationUtil
import com.example.presenter.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navHostController = rememberNavController()

                NavHost(navController = navHostController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onRouteDetail = {item ->
                                NavigationUtil.passItem(item)
                                navHostController.navigate(Screen.Detail(NavigationUtil.passItem(item)).sendRoute)
                            }
                        )
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
        }

    }

}
