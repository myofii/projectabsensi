package com.android.projectabsensi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LoginActivity : AppCompatActivity() {
    //definisikan object EditText dan Button
    lateinit var inputEmail: EditText
    lateinit var inputPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnDaftar: Button
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

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
            registerUser();
        })
    }
    fun registerUser() {
        //buat variabel lokal untuk menampung inputan dari Form
        val email = inputEmail.text.toString().trim { it <= ' ' }
        val password = inputPassword.text.toString().trim { it <= ' ' }

        //periksa inputan email, tampilkan toast jika kosong
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this@LoginActivity, "Email harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        //periksa inputan password, tampilkan toast jika kosong
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this@LoginActivity, "Password harus diisi", Toast.LENGTH_SHORT).show()
            return
        }
        //jika input email dan password oke, lanjutkan dengan FirebaseAuth
        firebaseAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Registrasi selesai", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Registrasi gagal, email sudah digunakan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    fun createUser() {
        val uid = firebaseAuth!!.currentUser!!.uid
        val reference = FirebaseDatabase.getInstance().reference
        val uidRef = reference.child("users").child(uid)
        uidRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    val intent = Intent(this@LoginActivity, CreateUserActivity::class)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("DB_ERR", databaseError.message)
            }
        })
    }
}