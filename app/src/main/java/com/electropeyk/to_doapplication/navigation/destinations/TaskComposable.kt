package com.electropeyk.to_doapplication.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.electropeyk.to_doapplication.ui.screens.task.TaskScreen
import com.electropeyk.to_doapplication.ui.viewmodels.SharedViewModel
import com.electropeyk.to_doapplication.util.Action
import com.electropeyk.to_doapplication.util.Constants.TASK_ARGUMENT_KEY
import com.electropeyk.to_doapplication.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen:(Action)->Unit,
    sharedViewModel: SharedViewModel
    ){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = {-it},
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId=taskId)
        }
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        
        LaunchedEffect(key1 = selectedTask) {
            if(selectedTask != null || taskId == -1){
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }
        
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigationToListScreen= navigateToListScreen
        )
    }
}