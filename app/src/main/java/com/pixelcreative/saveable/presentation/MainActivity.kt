package com.pixelcreative.saveable.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pixelcreative.saveable.navigation.Router
import com.pixelcreative.saveable.navigation.RouterImplementation
import com.pixelcreative.saveable.navigation.SaveableNavGraph
import com.pixelcreative.saveable.navigation.Screens
import com.pixelcreative.saveable.ui.theme.SaveableTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val route = navBackStackEntry?.destination?.route ?: Screens.Splash.route
            val router: Router = remember { RouterImplementation(navController, route) }
            SaveableTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    SaveableNavGraph(navController = navController, router = router)
                }
            }
        }
    }
}
