package com.example.languagelearning

import android.content.ContentValues
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
import com.example.languagelearning.MainActivity
import com.example.languagelearning.R

class WritingFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_writing, container, false)

        val writingBtnLesson1 : Button = view.findViewById(R.id.writing_btn_lesson1)
        writingBtnLesson1.setOnClickListener{
            levelSelect = 1
            Navigation.findNavController(view).navigate(R.id.writing_to_quiz)
        }

        val writingBtnLesson2 : Button = view.findViewById(R.id.writing_btn_lesson2)
        writingBtnLesson2.setOnClickListener{
            levelSelect = 2
            Navigation.findNavController(view).navigate(R.id.writing_to_quiz)
        }

        val writingBtnLesson3 : Button = view.findViewById(R.id.writing_btn_lesson3)
        writingBtnLesson3.setOnClickListener{
            levelSelect = 3
            Navigation.findNavController(view).navigate(R.id.writing_to_quiz)
        }

        val writingBtnLesson4 : Button = view.findViewById(R.id.writing_btn_lesson4)
        writingBtnLesson4.setOnClickListener{
            levelSelect = 4
            Navigation.findNavController(view).navigate(R.id.writing_to_quiz)
        }

        val writingBtnLesson5 : Button = view.findViewById(R.id.writing_btn_lesson5)
        writingBtnLesson5.setOnClickListener{
            levelSelect = 5
            Navigation.findNavController(view).navigate(R.id.writing_to_quiz)
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