package com.example.myapplicationsurvey.sample

import com.example.myapplicationsurvey.data.model.*
import java.util.UUID

object SampleData {

    fun sampleSurvey(): Survey {
        return Survey(
            id = UUID.randomUUID().toString(),
            title = "User Profile & Music Preferences",
            pages = listOf(
                // 页面1: 基础信息
                SurveyPage(
                    id = "page1", title = "Basic Info", questions = listOf(
                        TextQuestion(
                            id = "name", prompt = "What is your name?", placeholder = "Your Name"
                        ), TextQuestion(
                            id = "birthday",
                            prompt = "When is your birthday?",
                            placeholder = "YYYY-MM-DD"
                        ), SingleChoiceQuestion(
                            id = "gender",
                            prompt = "Your pronouns?",
                            options = listOf("He", "She", "They")
                        )
                    )
                ),
                // 页面2: 听歌习惯
                SurveyPage(
                    id = "page2", title = "Listening Habits", questions = listOf(
                        MultiChoiceQuestion(
                            id = "listening_habits",
                            prompt = "When do you usually listen to music?",
                            options = listOf("Commute", "Workout", "Study", "Bedtime")
                        ), MultiChoiceQuestion(
                            id = "music_vibes",
                            prompt = "What music genres do you like?",
                            options = listOf(
                                "Pop",
                                "Rock",
                                "Hip Hop / Rap",
                                "R&B (Rhythm and Blues)",
                                "Country",
                                "Electronic / Dance / EDM",
                                "Jazz",
                                "Blues",
                                "Classical",
                                "Soul",
                                "Funk",
                                "Reggae",
                                "Disco",
                                "Folk",
                                "Metal"
                            )
                        ), TextQuestion(
                            id = "favorite_artist",
                            prompt = "Your favorite artist?",
                            placeholder = "Favorite Artist"
                        )
                    )
                )
            )
        )
    }
}
