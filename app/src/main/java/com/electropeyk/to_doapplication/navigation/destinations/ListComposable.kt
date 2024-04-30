package com.electropeyk.to_doapplication.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.electropeyk.to_doapplication.ui.screens.list.ListScreen
import com.electropeyk.to_doapplication.util.Constants.LIST_ARGUMENT_KEY
import com.electropeyk.to_doapplication.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)->Unit
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}

