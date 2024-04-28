package com.electropeyk.to_doapplication.navigation

import androidx.navigation.NavHostController
import com.electropeyk.to_doapplication.util.Action
import com.electropeyk.to_doapplication.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = {action->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive = true}
        }
    }

    val task:(Int) -> Unit = {taskId->
        navController.navigate("task/${taskId}")
    }

}