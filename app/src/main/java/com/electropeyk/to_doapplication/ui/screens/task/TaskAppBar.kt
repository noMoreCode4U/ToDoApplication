package com.electropeyk.to_doapplication.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.electropeyk.to_doapplication.R
import com.electropeyk.to_doapplication.components.DisplayAlertDialog
import com.electropeyk.to_doapplication.data.models.Priority
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.ui.theme.topAppBarBackgroundColor
import com.electropeyk.to_doapplication.ui.theme.topAppBarContentColor
import com.electropeyk.to_doapplication.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigationToListScreen: (Action) -> Unit

){
    if(selectedTask == null){
        NewTaskAppBar(navigationToListScreen = navigationToListScreen)
    }else{
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigationToListScreen = navigationToListScreen
        )
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigationToListScreen: (Action) -> Unit
){
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigationToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colorScheme.topAppBarContentColor)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor),
        actions = {
            AddAction(onAddClicked = navigationToListScreen)
        }

    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
){
    IconButton(onClick = {onBackClicked(Action.NO_ACTION)}) {
        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
){
    IconButton(onClick = {onAddClicked(Action.ADD)}) {
        Icon(imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigationToListScreen: (Action) -> Unit
){
    TopAppBar(
        navigationIcon = {
                CloseAction(onCloseClicked = navigationToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor),
        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigationToListScreen = navigationToListScreen
            )
        }

    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
){
    IconButton(onClick = {onCloseClicked(Action.NO_ACTION)}) {
        Icon(imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigationToListScreen: (Action) -> Unit
){
    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task,selectedTask.title
        ),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title
        ) ,
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigationToListScreen(Action.DELETE) }
    )
    DeleteAction(onDeleteClicked = {openDialog = true})
    UpdateAction(onUpdateClicked = navigationToListScreen)
}


@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
){
    IconButton(onClick = {onDeleteClicked()}) {
        Icon(imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
){
    IconButton(onClick = {onUpdateClicked(Action.UPDATE)}) {
        Icon(imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@Composable
@Preview
fun NewTaskAppBarPreview(){
    NewTaskAppBar(navigationToListScreen = {})
}

@Composable
@Preview
fun ExistingTaskAppBarPreview(){
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 0,
            title = "stevdza-san",
            description = "Some random Text",
            priority = Priority.LOW
        ),
        navigationToListScreen = {}
    )
}