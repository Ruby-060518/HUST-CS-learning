package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.myapplicationsurvey.R

@Composable
fun VinylIconButton(resId: Int, onClick: () -> Unit, sizeDp: Int = 48) {
    Image(painter = painterResource(id = resId), contentDescription = null, modifier =
        Modifier.size(sizeDp.dp).clickable(onClick = onClick))
}