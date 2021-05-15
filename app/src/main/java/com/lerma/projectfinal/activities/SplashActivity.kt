package com.lerma.projectfinal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.lerma.projectfinal.R
import com.lerma.projectfinal.preferences.SharedPreferencesHelper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            val intent = if (SharedPreferencesHelper.getUserName(this).isNullOrBlank()) {
                 Intent(this, MainActivity::class.java)
            } else {
                Intent(this, MenuActivity::class.java)
            }

            intent.putExtra("KeyUser", SharedPreferencesHelper.getUserName(this).toString())
            intent.putExtra("KeyName", SharedPreferencesHelper.getName(this).toString())
            intent.putExtra("KeyLastName", SharedPreferencesHelper.getLastName(this).toString())

            startActivity(intent)
            finish()
        }, 2000)
    }
}