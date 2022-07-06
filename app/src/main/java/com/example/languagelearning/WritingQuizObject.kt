package com.example.languagelearning

import com.parse.ParseClassName
import com.parse.ParseObject

// quiz level : number
// question : String
// answer : String
@ParseClassName("WritingQuiz")
class WritingQuizObject : ParseObject() {

    fun getLevel(): Int? {
        return getInt(KEY_WRITING_QUIZ_LEVEL)
    }

    fun getQuestion(): String? {
        return getString(KEY_WRITING_QUESTION)
    }
    fun getAnswer(): String? {
        return getString(KEY_WRITING_ANSWER)
    }

    companion object {
        const val KEY_WRITING_QUIZ_LEVEL = "quizLevel"
        const val KEY_WRITING_QUESTION = "question"
        const val KEY_WRITING_ANSWER = "answer"
    }

}