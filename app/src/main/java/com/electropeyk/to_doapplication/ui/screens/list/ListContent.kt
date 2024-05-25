package com.electropeyk.to_doapplication.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.electropeyk.to_doapplication.data.models.Priority
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.ui.theme.LARGE_PADDING
import com.electropeyk.to_doapplication.ui.theme.PRIORITY_INDICATOR_SIZE
import com.electropeyk.to_doapplication.ui.theme.TASK_ITEM_ELEVATION
import com.electropeyk.to_doapplication.ui.theme.taskItemBackgroundColor
import com.electropeyk.to_doapplication.ui.theme.taskItemTextColor
import com.electropeyk.to_doapplication.util.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId:Int) -> Unit
){
    if(tasks is RequestState.Success){
        if(tasks.data.isEmpty()){
            EmptyContent()
        }else{
            DisplayTasks(
                tasks = tasks.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId:Int) -> Unit
){
    LazyColumn {
        items(
            items = tasks,
            key = {task ->
                task.id
            }
        ){task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )


        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId:Int) -> Unit
){
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = MaterialTheme.colorScheme.taskItemBackgroundColor,
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ){
        Column(modifier = Modifier
            .padding(all = LARGE_PADDING)
            .fillMaxWidth()) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.taskItemTextColor,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    contentAlignment = Alignment.CenterEnd){
                    Canvas(modifier = Modifier
                        .size(PRIORITY_INDICATOR_SIZE)) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
                
            }
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.taskItemTextColor,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
@Preview
fun TaskItemPreview(){
    TaskItem(
        toDoTask = ToDoTask(
        id = 0,
        title = "Title",
        description = "Some random text.",
        priority = Priority.MEDIUM
    ),
        navigateToTaskScreen = {})
}