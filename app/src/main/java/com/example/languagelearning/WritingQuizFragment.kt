package com.example.languagelearning

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.*
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlin.random.Random


class WritingQuizFragment : Fragment() {

    lateinit var textView: TextView
    lateinit var recognizer: DigitalInkRecognizer
    lateinit var btnTest: Button
    lateinit var textQuestion: TextView
    lateinit var btnHint : Button
    lateinit var progressBar : ProgressBar
    val remoteModelManager = RemoteModelManager.getInstance()
    var model: DigitalInkRecognitionModel? = null
    var answer = String()
    var correctAnswers= 0
    var maxQuestions = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_writing_quiz, container, false)
        val btnErase: Button = view.findViewById(R.id.clearCanvas)
        val btnWritingQuit: Button = view.findViewById(R.id.btn_writing_quit)
        textView = view.findViewById(R.id.lessonNumber)
        textView.text = "Correct Answers: $correctAnswers/5"
        textQuestion = view.findViewById(R.id.textQuestion)
        val btnAnswer : Button = view.findViewById(R.id.enterAnswer)
        btnTest = view.findViewById(R.id.writingCheck)
        btnHint = view.findViewById(R.id.btn_hint)
        initializeRecognition()
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.max = maxQuestions
        val textOutput: TextView = view.findViewById(R.id.textOutput)
        val drawingView: DrawingView = view.findViewById(R.id.drawing_view)
        var thisInk: Ink
        getQuestion()

        btnErase.setOnClickListener {
            drawingView.clear()
            textOutput.text = ""
        }
        btnWritingQuit.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.quiz_to_writing)
        }

        btnTest.setOnClickListener {
            thisInk = drawingView.getInk()
            recognizer = DigitalInkRecognition.getClient(DigitalInkRecognizerOptions.builder(model!!).build() )
            recognizer.recognize(thisInk)
                .addOnSuccessListener { result: RecognitionResult ->
                    textOutput.text = result.candidates[0].text
                }
                .addOnFailureListener { e: Exception ->
                    Log.e("Digital Ink Test", "Error during recognition: $e")
                }
            }
        btnAnswer.setOnClickListener{
            if(answer == textOutput.text) {
                //Log.e(ContentValues.TAG, "correct answer")
                correctAnswers++
                textView.text = "Correct Answers: $correctAnswers/5"
                updateProgressBar()
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
                        Navigation.findNavController(view).navigate(R.id.quiz_to_writing)
                    }
                }
                getQuestion()
                drawingView.clear()
                textOutput.text = ""
                getFragmentManager()?.beginTransaction()?.detach(this)?.attach(this)?.commit()
            }
            else {
                //Log.e(ContentValues.TAG, "Wrong Answer")
                Toast.makeText(activity,"Try Again!", Toast.LENGTH_SHORT).show()
            }
        }
        btnHint.setOnClickListener{
            Toast.makeText(activity,answer, Toast.LENGTH_SHORT).show()
        }

        return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initializeRecognition(){
        val modelIdentifier: DigitalInkRecognitionModelIdentifier? =
            DigitalInkRecognitionModelIdentifier.fromLanguageTag("ko-KR")
        model = DigitalInkRecognitionModel.builder(modelIdentifier!!).build()
        remoteModelManager.download(model!!, DownloadConditions.Builder().build()).addOnSuccessListener {
            Log.i("InkSample", "Model Downloaded")
            btnTest.isEnabled = true
        }. addOnFailureListener {  e: Exception ->
            Log.e("InkSample", "Model failed $e")
        }
    }

    fun getQuestion() {
        val query : ParseQuery<WritingQuizObject> = ParseQuery.getQuery(WritingQuizObject::class.java)
        query.whereEqualTo("quizLevel", WritingFragment.levelSelect)
        query.findInBackground(object : FindCallback<WritingQuizObject> {
            override fun done(questions: MutableList<WritingQuizObject>?, e: ParseException?) {
                if (e != null) {
                    // something went wrong
                    Log.e(TAG, "Error getting questions")
                } else {
                    if (questions != null) {
                        val questionSelect = (0..9).shuffled().last()
                        textQuestion.text = questions[questionSelect].getQuestion()
                        answer = questions[questionSelect].getAnswer().toString()
                    }
                }
            }
        })
    }

    fun updateProgressBar() {
        ObjectAnimator.ofInt(progressBar, "progress", correctAnswers)
            .setDuration(100)
            .start()
    }

    companion object {
        var path = Path()
        var paintBrush = Paint()
        const val TAG = "Writing Quiz Fragment"
    }
}

//Log.i(TAG, "Object: " + questions[0].getQuestion() + " " + questions[0].getAnswer())
/*
                        for(question in questions) {
                            Log.i(TAG, "Object: " + question.getQuestion() + " " + question.getAnswer())
                        }
 */