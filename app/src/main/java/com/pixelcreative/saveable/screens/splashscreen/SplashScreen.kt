package com.pixelcreative.saveable.screens.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pixelcreative.saveable.navigation.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(router: Router) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SplashScreen Screen",
            modifier = Modifier
        )
    }
    LaunchedEffect(Unit) {
        scope.launch {
            delay(2000)
            router.goToExpenseScreen()
        }
    }
}
