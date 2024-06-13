@file:OptIn(ExperimentalMaterial3Api::class)

package com.electropeyk.to_doapplication.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.electropeyk.to_doapplication.R
import com.electropeyk.to_doapplication.data.models.Priority
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.ui.theme.HighPriorityColor
import com.electropeyk.to_doapplication.ui.theme.LARGEST_PADDING
import com.electropeyk.to_doapplication.ui.theme.LARGE_PADDING
import com.electropeyk.to_doapplication.ui.theme.PRIORITY_INDICATOR_SIZE
import com.electropeyk.to_doapplication.ui.theme.TASK_ITEM_ELEVATION
import com.electropeyk.to_doapplication.ui.theme.taskItemBackgroundColor
import com.electropeyk.to_doapplication.ui.theme.taskItemTextColor
import com.electropeyk.to_doapplication.util.Action
import com.electropeyk.to_doapplication.util.RequestState
import com.electropeyk.to_doapplication.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState:RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId:Int) -> Unit
){

    if(sortState is RequestState.Success){
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if(searchAppBarState == SearchAppBarState.TRIGGERED){
                    if(searchedTasks is RequestState.Success){
                        HandleListContent(
                            tasks = searchedTasks.data,
                            onSwipeToDelete = onSwipeToDelete,
                            navigateToTaskScreen = navigateToTaskScreen
                        )
                    }
                }
            }
            sortState.data == Priority.NONE -> {
                if(allTasks is RequestState.Success){
                    HandleListContent(
                        tasks = allTasks.data,
                        onSwipeToDelete = onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen)
            }
            sortState.data == Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriorityTasks,
                    onSwipeToDelete = onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen)
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks:List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    if(tasks.isEmpty()){
        EmptyContent()
    }else{
        DisplayTasks(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId:Int) -> Unit
){
    val threshold: Float = with(LocalConfiguration.current) {
        (this.screenWidthDp * 30).dp.value
    }
    val deleteValue = SwipeToDismissBoxValue.EndToStart

    LazyColumn {
        items(
            items = tasks,
            key = {task ->
                task.id
            }
        ){task ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    if (it == deleteValue){
                        onSwipeToDelete(Action.DELETE,task)
                        true
                    } else false
                },
                positionalThreshold = {threshold}
            )

            val degree by animateFloatAsState(
                targetValue = if(dismissState.progress <= 0.2f)
                0f
                else
                -45f,
                label = ""
            )

            var itemAppeared by remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = true) {
                itemAppeared = true
            }

            AnimatedVisibility(
                visible = itemAppeared && (degree != 45f),
                enter = expandVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            ) {
                SwipeToDismissBox(
                    state = dismissState ,
                    backgroundContent =
                    {
                        RedBackground(degree)
                    },
                    enableDismissFromStartToEnd = false
                ) {
                    TaskItem(
                        toDoTask = task,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

        }
    }
}

@Composable
fun RedBackground(
    degree:Float
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(HighPriorityColor)
        .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd){
        Icon(
            modifier = Modifier.rotate(degrees = degree),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
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