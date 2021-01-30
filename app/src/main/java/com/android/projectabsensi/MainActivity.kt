package com.android.projectabsensi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //splashscreen
        Handler().postDelayed({ //setelah timer habis langsung pindah ke LoginActivity
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }, 1000) //delayMilis ini yang mengatur lama waktu tunda, dalam mili-detik
    }
}