package com.electropeyk.to_doapplication.navigation.destinations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.electropeyk.to_doapplication.ui.screens.list.ListScreen
import com.electropeyk.to_doapplication.ui.viewmodels.SharedViewModel
import com.electropeyk.to_doapplication.util.Constants.LIST_ARGUMENT_KEY
import com.electropeyk.to_doapplication.util.Constants.LIST_SCREEN
import com.electropeyk.to_doapplication.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)->Unit,
    sharedViewModel: SharedViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}

