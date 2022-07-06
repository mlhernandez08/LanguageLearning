package com.example.languagelearning

import com.parse.ParseClassName
import com.parse.ParseObject
import org.json.JSONArray

// lessonNum : Int
// answers : Array
// correctAnswer : String
// question : String
@ParseClassName("VocabQuiz")
class VocabQuizObject : ParseObject() {

    fun getLessonNum() : Int? {
        return getInt(KEY_LESSON_NUM)
    }

    fun getAnswers() : JSONArray? {
        return getJSONArray(KEY_VOCAB_ANSWERS)
    }

    fun getCorrectAnswer() : String? {
        return getString(KEY_CORRECT_ANSWER)
    }

    fun getQuestion() : String? {
        return getString(KEY_VOCAB_QUESTION)
    }

    companion object {
        const val KEY_LESSON_NUM = "lessonNum"
        const val KEY_VOCAB_ANSWERS = "answers"
        const val KEY_CORRECT_ANSWER = "correctAnswer"
        const val KEY_VOCAB_QUESTION = "question"
    }
}