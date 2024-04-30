package com.electropeyk.to_doapplication.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.electropeyk.to_doapplication.R
import com.electropeyk.to_doapplication.ui.theme.fabBackgroundColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int) -> Unit
) {
    Scaffold(
        topBar = {
            ListAppBar()
                 },
        content = {},
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
@Preview
private fun ListScreenPreview(){
    ListScreen(navigateToTaskScreen = {})
}