package com.example.myapplicationsurvey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.myapplicationsurvey.Navigation.AppNavGraph
import com.example.myapplicationsurvey.ui.theme.MyApplicationSurveyTheme
import com.example.myapplicationsurvey.viewmodel.SurveyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationSurveyTheme {
                // 在 Activity 范围内创建并记住一个 ViewModel 实例，传给整个导航图
                val viewModel = remember { SurveyViewModel(application) }
                AppNavGraph(viewModel = viewModel)
            }
        }
    }
}