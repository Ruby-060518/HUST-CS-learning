package com.example.myapplicationsurvey.Navigation


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Survey : Screen("survey")
    object Results : Screen("results")
}