package com.example.languagelearning

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import org.json.JSONArray

class VocabQuizFragment : Fragment() {
    lateinit var textQuestion: TextView
    lateinit var textView: TextView
    var answer = String()
    var correctAnswers = 0
    var maxQuestions = 5
    lateinit var btnAnswer1 : Button
    lateinit var btnAnswer2 : Button
    lateinit var btnAnswer3 : Button
    lateinit var btnAnswer4 : Button
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vocab_quiz, container, false)
        textView = view.findViewById(R.id.lessonNumber)
        val btnVocabQuit: Button = view.findViewById(R.id.btn_vocab_quit)
        textView.text = "Correct Answers: $correctAnswers/5"
        btnAnswer1 = view.findViewById(R.id.btn_answer_1)
        btnAnswer2 = view.findViewById(R.id.btn_answer_2)
        btnAnswer3 = view.findViewById(R.id.btn_answer_3)
        btnAnswer4 = view.findViewById(R.id.btn_answer_4)
        textQuestion = view.findViewById(R.id.tv_question)
        textQuestion.text = ""
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.max = maxQuestions
        getQuestion()

        btnVocabQuit.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.quiz_to_vocab)
        }

        btnAnswer1.setOnClickListener{
            if (btnAnswer1.text == answer) {
                correctAnswers++
                textView.text = "Correct Answers: $correctAnswers/5"
                if(correctAnswers >= maxQuestions) {
                    val customView = View.inflate(context, R.layout.custom_alert_layout, null)

                    val builder = AlertDialog.Builder(context)
                    builder.setView(customView)
                    val dialog = builder.create()
                    dialog.show()
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    val btnDismiss : Button = customView.findViewById(R.id.btnDismiss)
                    btnDismiss.setOnClickListener {
                        dialog.dismiss()
                        Navigation.findNavController(view).navigate(R.id.quiz_to_vocab)
                    }

                }
                getQuestion()
                getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                updateProgressBar()
            } else {
                btnAnswer1.isEnabled = false
            }
        }

        btnAnswer2.setOnClickListener{
            if (btnAnswer2.text == answer) {
                correctAnswers++
                textView.text = "Correct Answers: $correctAnswers/5"
                if(correctAnswers >= maxQuestions) {
                    val customView = View.inflate(context, R.layout.custom_alert_layout, null)

                    val builder = AlertDialog.Builder(context)
                    builder.setView(customView)
                    val dialog = builder.create()
                    dialog.show()
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    val btnDismiss : Button = customView.findViewById(R.id.btnDismiss)
                    btnDismiss.setOnClickListener {
                        dialog.dismiss()
                        Navigation.findNavController(view).navigate(R.id.quiz_to_vocab)
                    }

                }
                getQuestion()
                getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                updateProgressBar()
            } else {
                btnAnswer2.isEnabled = false
            }
        }

        btnAnswer3.setOnClickListener{
            if (btnAnswer3.text == answer) {
                correctAnswers++
                textView.text = "Correct Answers: $correctAnswers/5"
                if(correctAnswers >= maxQuestions) {
                    val customView = View.inflate(context, R.layout.custom_alert_layout, null)

                    val builder = AlertDialog.Builder(context)
                    builder.setView(customView)
                    val dialog = builder.create()
                    dialog.show()
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    val btnDismiss : Button = customView.findViewById(R.id.btnDismiss)
                    btnDismiss.setOnClickListener {
                        dialog.dismiss()
                        Navigation.findNavController(view).navigate(R.id.quiz_to_vocab)
                    }
                }
                getQuestion()
                getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                updateProgressBar()
            } else {
                btnAnswer3.isEnabled = false
            }
        }

        btnAnswer4.setOnClickListener{
            if (btnAnswer4.text == answer) {
                correctAnswers++
                textView.text = "Correct Answers: $correctAnswers/5"
                if(correctAnswers >= maxQuestions) {
                    val customView = View.inflate(context, R.layout.custom_alert_layout, null)

                    val builder = AlertDialog.Builder(context)
                    builder.setView(customView)
                    val dialog = builder.create()
                    dialog.show()
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    val btnDismiss : Button = customView.findViewById(R.id.btnDismiss)
                    btnDismiss.setOnClickListener {
                        dialog.dismiss()
                        Navigation.findNavController(view).navigate(R.id.quiz_to_vocab)
                    }
                }
                getQuestion()
                getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
                updateProgressBar()
            } else {
                btnAnswer4.isEnabled = false
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun getQuestion() {
        btnAnswer1.isEnabled = true
        btnAnswer2.isEnabled = true
        btnAnswer3.isEnabled = true
        btnAnswer4.isEnabled = true
        val query : ParseQuery<VocabQuizObject> = ParseQuery.getQuery(VocabQuizObject::class.java)
        query.whereEqualTo("lessonNum", VocabFragment.levelSelect)
        query.findInBackground(object : FindCallback<VocabQuizObject> {
            override fun done(questions: MutableList<VocabQuizObject>?, e: ParseException?) {
                if (e != null) {
                    // something went wrong
                    Log.e(WritingQuizFragment.TAG, "Error getting questions")
                } else {
                    if (questions != null) {
                        val questionSelect = (0..9).shuffled().last()
                        val options : JSONArray? = questions[questionSelect].getAnswers()
                        textQuestion.text = questions[questionSelect].getQuestion()
                        answer = questions[questionSelect].getCorrectAnswer().toString()
                        if (options != null) {
                            btnAnswer1.text = options.getString(0)
                            btnAnswer2.text = options.getString(1)
                            btnAnswer3.text = options.getString(2)
                            btnAnswer4.text = options.getString(3)
                        }
                        //answer = questions[questionSelect].getAnswer().toString()
                    }
                }
            }
        })
        return
    }

    fun updateProgressBar() {
        ObjectAnimator.ofInt(progressBar, "progress", correctAnswers)
            .setDuration(100)
            .start()
    }


    companion object {
        const val TAG = "Vocab Quiz Fragment"
    }
}
/*

                        for(question in questions) {
                            Log.i(TAG, "Object: " + question.getQuestion() + " " + question.getAnswers() + " " + question.getCorrectAnswer())
                        }

                val alertDialogBuilder = AlertDialog.Builder(context)
                alertDialogBuilder.setMessage("Correct")
                alertDialogBuilder.setCancelable(true)
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
 */