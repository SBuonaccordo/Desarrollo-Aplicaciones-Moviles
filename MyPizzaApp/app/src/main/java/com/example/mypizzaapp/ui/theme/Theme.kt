package com.example.mypizzaapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun PizzeriaTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                  content: @Composable () -> Unit){
    val colorSheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorSheme,
        typography = Typography,
        content = content
    )
}