package com.example.languagelearning

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class VocabFragment : Fragment() {

    //private var activity: MainActivity?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activity = activity

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_vocab, container, false)

        val btnLesson1 : Button = view.findViewById(R.id.btn_lesson1)
        btnLesson1.setOnClickListener{
            Log.e(TAG, "lesson 1 button pressed");
            val lessonTitle = "Lesson 1 test"
            val bundle = Bundle()
            bundle.putString("Lesson 1", lessonTitle)
            val fragment = VocabQuizFragment()
            fragment.arguments = bundle
            Log.e(TAG, "bundle contents " + bundle.get("Lesson 1"))
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }
        val btnLesson2 : Button = view.findViewById(R.id.btn_lesson2)
        btnLesson2.setOnClickListener{
            Log.e(TAG, "lesson 2 button pressed");
        }

        val btnLesson3 : Button = view.findViewById(R.id.btn_lesson3)
        btnLesson3.setOnClickListener{
            Log.e(TAG, "lesson 3 button pressed");
        }

        val btnLesson4 : Button = view.findViewById(R.id.btn_lesson4)
        btnLesson4.setOnClickListener{
            Log.e(TAG, "lesson 4 button pressed");
        }

        val btnLesson5 : Button = view.findViewById(R.id.btn_lesson5)
        btnLesson5.setOnClickListener{
            Log.e(TAG, "lesson 5 button pressed");
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}