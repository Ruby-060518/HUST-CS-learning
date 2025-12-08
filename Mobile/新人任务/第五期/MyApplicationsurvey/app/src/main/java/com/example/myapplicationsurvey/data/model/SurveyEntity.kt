package com.example.myapplicationsurvey.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "surveys")
data class SurveyEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val questionsJson: String   // 你原本的 Survey 问题列表序列化成 JSON 保存
)


