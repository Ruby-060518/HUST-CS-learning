package com.example.pwr.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun MovieComposeTheme(content: @Composable () -> Unit) {
    val colors = LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = androidx.compose.material3.Typography(),
        content = {
            Surface {
                content()
            }
        }
    )
}

@Composable
fun PwrTheme(content: @Composable () -> Unit) = MovieComposeTheme(content)
