package com.example.languagelearning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.parse.ParseUser

class SettingsFragment : Fragment() {

    private var activity: MainActivity?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)

        val btnLogOut : Button = view.findViewById(R.id.btn_logOut)
        btnLogOut.setOnClickListener{
            ParseUser.logOut()
            val currentUser = ParseUser.getCurrentUser()
            gotToLoginActivity()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun gotToLoginActivity() {
        val intent  = Intent(getActivity(), LoginActivity::class.java)
        startActivity(intent)
    }
}