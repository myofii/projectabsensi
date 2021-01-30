package com.android.projectabsensi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    //definisikan object EditText dan Button
    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnDaftar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //inisialisasi object ke komponen UI
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnDaftar = findViewById(R.id.btnDaftar)

        //setOnClickListener pada btnLogin
        btnLogin.setOnClickListener(View.OnClickListener { //aksi ketika btnLogin di-klik
            Toast.makeText(applicationContext, "Button Login di-klik", Toast.LENGTH_LONG).show()
        })

        //setOnClickListener pada btnDaftar
        btnDaftar.setOnClickListener(View.OnClickListener { //aksi ketika btnDaftar di-klik
            Toast.makeText(applicationContext, "Button Daftar di-klik", Toast.LENGTH_LONG).show()
        })
    }
}