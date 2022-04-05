package com.example.languagelearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.Parse
import com.parse.ParseUser
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //check if user is logged in
        if (ParseUser.getCurrentUser() !=null) {
            gotToMainActivity()
        }
        
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }
        findViewById<Button>(R.id.btn_signUp).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String) {
        val user = ParseUser()

        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // user has successfully created a new account
            } else {
                e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e->
            if (user != null) {
                Log.i(TAG, "Logged in")
                //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                gotToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error Logging in", Toast.LENGTH_LONG).show()
            }})
        )
    }

    private fun gotToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object {
        const val TAG = "LoginActivity"
    }
}