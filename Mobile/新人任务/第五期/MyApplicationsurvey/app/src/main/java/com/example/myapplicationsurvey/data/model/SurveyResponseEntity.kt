package com.example.myapplicationsurvey.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "survey_responses")
data class SurveyResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // 原有: val surveyId: String, val answersJson: String, val timestamp: Long
    // 改为列式存储：把问卷的各项答案作为独立列保存
    val surveyId: String,

    // 基础信息
    val name: String?,
    val avatarUri: String?,
    val birthdayEpochDay: Long?, // 以 LocalDate.toEpochDay() 保存，可空
    val pronouns: String?,       // He / She / They

    // 听歌时段
    val listenHabitCommute: Boolean?,
    val listenHabitBedtime: Boolean?,
    val listenHabitWorkout: Boolean?,
    val listenHabitStudy: Boolean?,

    // 音乐风格（15项）
    val likeJazz: Boolean?,
    val likeRock: Boolean?,
    val likePop: Boolean?,
    val likeHipHop: Boolean?,
    val likeClassical: Boolean?,
    val likeRnB: Boolean?,
    val likeCountry: Boolean?,
    val likeEdm: Boolean?,
    val likeBlues: Boolean?,
    val likeSoul: Boolean?,
    val likeFunk: Boolean?,
    val likeReggae: Boolean?,
    val likeDisco: Boolean?,
    val likeFolk: Boolean?,
    val likeMetal: Boolean?,

    // 喜爱歌手
    val favoriteArtist: String?,

    // 时间戳
    val timestamp: Long
)
