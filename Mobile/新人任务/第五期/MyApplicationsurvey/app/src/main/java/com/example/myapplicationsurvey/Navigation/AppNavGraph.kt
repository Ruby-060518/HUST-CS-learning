package com.example.myapplicationsurvey.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.myapplicationsurvey.ui.login.LoginScreen
import com.example.myapplicationsurvey.ui.survey.SurveyScreen
import com.example.myapplicationsurvey.ui.home.HomeScreen
import com.example.myapplicationsurvey.viewmodel.SurveyViewModel


@Composable
fun AppNavGraph(viewModel: SurveyViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(onContinue = { name, avatar ->
// create sample survey and start
                val sample = com.example.myapplicationsurvey.sample.SampleData.sampleSurvey()
                viewModel.startSurvey(sample)
                navController.navigate(Screen.Survey.route)
            })
        }
        composable(Screen.Survey.route) {
            SurveyScreen(viewModel = viewModel, onFinish = { navController.navigate(Screen.Results.route) })
        }
        composable(Screen.Results.route) {
            HomeScreen(responses = viewModel.savedResponses.value)
        }
    }
}