package com.example.myapplicationsurvey.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val name: String?, val avatarUri: String?
)