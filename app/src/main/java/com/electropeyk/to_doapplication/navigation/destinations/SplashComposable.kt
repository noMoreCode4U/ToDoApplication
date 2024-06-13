package com.electropeyk.to_doapplication.navigation.destinations

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.electropeyk.to_doapplication.ui.screens.splash.SplashScreen
import com.electropeyk.to_doapplication.util.Constants

fun NavGraphBuilder.splashComposable(
    navigateToListScreen:()->Unit,
){
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = {-it},
                animationSpec = tween(300)
            )
        }
    ){
        SplashScreen(navigateToListScreen = navigateToListScreen)

    }
}