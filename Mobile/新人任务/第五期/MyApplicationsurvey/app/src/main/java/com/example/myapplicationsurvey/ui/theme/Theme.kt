package com.example.myapplicationsurvey.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = WarmOrangeRed,
    onPrimary = RiceWhite,
    background = Cream,
    onBackground = DeepBlack
)


@Composable
fun MyApplicationSurveyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}