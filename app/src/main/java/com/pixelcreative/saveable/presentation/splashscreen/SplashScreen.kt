package com.pixelcreative.saveable.presentation.splashscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pixelcreative.saveable.navigation.Router

@Composable
fun SplashScreen(router: Router) {
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Splash Screen", modifier = Modifier.clickable {
            router.goToExpenseScreen()
        })
    }
}