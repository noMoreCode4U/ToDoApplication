package com.electropeyk.to_doapplication.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.electropeyk.to_doapplication.R
import com.electropeyk.to_doapplication.ui.theme.topAppBarBackgroundColor
import com.electropeyk.to_doapplication.ui.theme.topAppBarContentColor
import com.electropeyk.to_doapplication.util.Action

@Composable
fun TaskAppBar(
    navigationToListScreen: (Action) -> Unit

){
    NewTaskAppBar(navigationToListScreen = navigationToListScreen)
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
    IconButton(onClick = {onAddClicked(Action.NO_ACTION)}) {
        Icon(imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colorScheme.topAppBarContentColor)
    }

}

@Composable
@Preview
fun NewTaskAppBarPreview(){
    NewTaskAppBar(navigationToListScreen = {})
}