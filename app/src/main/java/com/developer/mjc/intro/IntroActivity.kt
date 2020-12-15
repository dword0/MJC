package com.developer.mjc.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.mjc.R
import java.util.*
import kotlin.concurrent.schedule

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

    }

    override fun onResume() {
        super.onResume()

        Timer("Startup",false).schedule(1000){

            startIntoScreen()
        }
    }

    private fun startIntoScreen() {
        val intent = Intent(this,IntroNavigationActivity::class.java)
        startActivity(intent)
    }
}