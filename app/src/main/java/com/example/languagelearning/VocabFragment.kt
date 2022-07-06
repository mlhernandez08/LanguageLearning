package com.example.languagelearning

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class VocabFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_vocab, container, false)

        val btnLesson1 : Button = view.findViewById(R.id.btn_lesson1)
        btnLesson1.setOnClickListener{
            levelSelect = 1
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }
        val btnLesson2 : Button = view.findViewById(R.id.btn_lesson2)
        btnLesson2.setOnClickListener{
            levelSelect = 2
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }

        val btnLesson3 : Button = view.findViewById(R.id.btn_lesson3)
        btnLesson3.setOnClickListener{
            levelSelect = 3
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }

        val btnLesson4 : Button = view.findViewById(R.id.btn_lesson4)
        btnLesson4.setOnClickListener{
            levelSelect = 4
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }

        val btnLesson5 : Button = view.findViewById(R.id.btn_lesson5)
        btnLesson5.setOnClickListener{
            levelSelect = 5
            Navigation.findNavController(view).navigate(R.id.vocab_to_quiz)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        var levelSelect = 0
    }
}