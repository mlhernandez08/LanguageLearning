package com.example.languagelearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val iv_splashIcon : ImageView = findViewById(R.id.iv_splashIcon)
        iv_splashIcon.alpha = 0f
        iv_splashIcon.animate().setDuration(1500).alpha(1f).withEndAction() {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}