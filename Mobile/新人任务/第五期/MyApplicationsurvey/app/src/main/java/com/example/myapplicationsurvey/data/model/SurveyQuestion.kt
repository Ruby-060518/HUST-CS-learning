package com.example.myapplicationsurvey.data.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Question {
    abstract val id: String
    abstract val prompt: String
}

@Serializable
data class SingleChoiceQuestion(override val id: String, override val prompt: String, val options:
List<String>) : Question()

@Serializable
data class MultiChoiceQuestion(override val id: String, override val prompt: String, val options:List<String>) : Question()

@Serializable
data class TextQuestion(override val id: String, override val prompt: String, val placeholder: String =
    "") : Question()