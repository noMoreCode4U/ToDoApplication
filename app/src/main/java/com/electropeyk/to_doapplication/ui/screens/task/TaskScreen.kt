package com.electropeyk.to_doapplication.ui.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.electropeyk.to_doapplication.data.models.Priority
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigationToListScreen: (Action) -> Unit
){
    Scaffold(
        topBar = {
                 TaskAppBar(
                     selectedTask = selectedTask,
                     navigationToListScreen = navigationToListScreen
                 )
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
            ) {
                TaskContent(
                    title = "",
                    onTitleChange = {},
                    description = "",
                    onDescriptionChange = {},
                    priority = Priority.LOW,
                    onPrioritySelected = {}
                )
            }
        }
    )
}