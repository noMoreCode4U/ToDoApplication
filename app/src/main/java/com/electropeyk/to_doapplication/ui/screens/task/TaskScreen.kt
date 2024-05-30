package com.electropeyk.to_doapplication.ui.screens.task

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.electropeyk.to_doapplication.data.models.Priority
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.ui.viewmodels.SharedViewModel
import com.electropeyk.to_doapplication.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigationToListScreen: (Action) -> Unit
){

    val title:String by sharedViewModel.title
    val description:String by sharedViewModel.description
    val priority:Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
                 TaskAppBar(
                     selectedTask = selectedTask,
                     navigationToListScreen = {action ->
                         if(action == Action.NO_ACTION){
                             navigationToListScreen(action)
                         }else{
                             if(sharedViewModel.validateFields()){
                                 navigationToListScreen(action)
                             }else{
                                displayToast(context = context)
                             }
                         }
                     }
                 )
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = {title->
                        sharedViewModel.updateTitle(title)
                    },
                    description = description,
                    onDescriptionChange = {description->
                        sharedViewModel.description.value = description
                    },
                    priority = priority,
                    onPrioritySelected = {priority->
                        sharedViewModel.priority.value = priority
                    }
                )
            }
        }
    )
}

fun displayToast(context: Context){
    Toast.makeText(context,"Fields Empty",Toast.LENGTH_SHORT).show()
}
