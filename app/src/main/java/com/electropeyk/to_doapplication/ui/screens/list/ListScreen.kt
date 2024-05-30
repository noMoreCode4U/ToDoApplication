package com.electropeyk.to_doapplication.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.electropeyk.to_doapplication.R
import com.electropeyk.to_doapplication.ui.theme.fabBackgroundColor
import com.electropeyk.to_doapplication.ui.theme.scaffoldContainerColor
import com.electropeyk.to_doapplication.ui.viewmodels.SharedViewModel
import com.electropeyk.to_doapplication.util.Action
import com.electropeyk.to_doapplication.util.SearchAppBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState


    val snackbarHostState = remember { SnackbarHostState() }

    DisplaySnackBar(
        snackbarHostState = snackbarHostState,
        handleDataBaseActions = {sharedViewModel.handleDatabaseActions(action = action)},
        taskTitle = sharedViewModel.title.value,
        action = action
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            ListAppBar(sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
                 },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
            ) {
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen)}
                  },
        containerColor = MaterialTheme.colorScheme.scaffoldContainerColor,
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked:(taskId:Int) -> Unit
){
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
    },
        containerColor = MaterialTheme.colorScheme.fabBackgroundColor

    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    snackbarHostState:SnackbarHostState,
    handleDataBaseActions:() -> Unit,
    taskTitle:String,
    action: Action
){
    handleDataBaseActions()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if(action != Action.NO_ACTION){
            scope.launch {
                val snackBarResult = snackbarHostState.showSnackbar(
                    message = "${action.name}: $taskTitle",
                    actionLabel = "OK")
            }
        }
    }
}

