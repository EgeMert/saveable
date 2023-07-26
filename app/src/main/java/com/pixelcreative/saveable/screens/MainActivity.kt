package com.pixelcreative.saveable.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.navigation.RouterImplementation
import com.pixelcreative.saveable.navigation.SaveableNavGraph
import com.pixelcreative.saveable.navigation.Screens
import com.pixelcreative.saveable.navigation.bottomnavigation.BottomNavigationBar
import com.pixelcreative.saveable.ui.theme.BluishPurple
import com.pixelcreative.saveable.ui.theme.SaveableTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val systemUiController: SystemUiController = rememberSystemUiController()
            DisposableEffect(systemUiController) {
                systemUiController.setStatusBarColor(BluishPurple)
                systemUiController.setSystemBarsColor(BluishPurple)
                onDispose { }
            }

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val route = navBackStackEntry?.destination?.route ?: Screens.SplashScreen.route
            val router: Router = remember { RouterImplementation(navController, route) }
            var canShowBottomSheet by rememberSaveable { mutableStateOf(false) }

            SaveableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(bottomBar = {

                        if (route != Screens.SplashScreen.route) {
                            BottomNavigationBar(
                                navController = navController,
                                bottomBarState = canShowBottomSheet
                            )
                        }
                    }, content = {
                        SaveableNavGraph(
                            navController = navController,
                            router = router,
                            hideBottomSheet = {
                                canShowBottomSheet = it
                            })
                    })
                }
            }
        }
    }
}
