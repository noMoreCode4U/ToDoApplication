package com.electropeyk.to_doapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200  = Color(0xFF03DAC5)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val LightGrayColor = Color(0xFFFCFCFC)
val MediumGrayColor = Color(0xFF9C9C9C)
val DarkGrayColor = Color(0xFF141414)


val LowPriorityColor = Color(0xFF00c988)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGrayColor


val ColorScheme.splashScreenBackground:Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.Black else Purple700

val ColorScheme.scaffoldContainerColor:Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.DarkGray else Color.White

val ColorScheme.fabBackgroundColor:Color
    @Composable
    get() = if(isSystemInDarkTheme()) Purple700 else Teal200

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) LightGrayColor else Color.White

val ColorScheme.topAppBarBackgroundColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.Black else Purple500

val ColorScheme.taskItemBackgroundColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.DarkGray else Color.White

val ColorScheme.taskItemTextColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray