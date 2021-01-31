package com.android.projectabsensi

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CreateUserActivity : AppCompatActivity() {
    private var uid: String? = null
    private var nama: EditText? = null
    private var telepon: EditText? = null
    private var email: EditText? = null
    private var database: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        nama = findViewById(R.id.editNama)
        telepon = findViewById(R.id.editTelepon)
        email = findViewById(R.id.editEmail)

        //ambil referensi Firebase
        database = FirebaseDatabase.getInstance().reference
        val firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.uid
        email.setText(firebaseAuth.currentUser!!.email)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            //buat variabel lokal
            val n = nama.getText().toString().trim { it <= ' ' }
            val m = email.getText().toString().trim { it <= ' ' }
            val t = telepon.getText().toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(n) && TextUtils.isEmpty(t) && TextUtils.isEmpty(m)) {
                Toast.makeText(this@CreateUserActivity, "Semua harus diisi", Toast.LENGTH_LONG)
                    .show()
            } else {
                //susun data sesuai model
                val users = Users(n, m, t, false)
                //akses ke (table) users
                database!!.child("users")
                    .child(uid!!) //buat child (primary key berdasar uid auth)
                    .setValue(users) //simpan data di dalamnya
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@CreateUserActivity,
                            "Data user berhasil disimpan",
                            Toast.LENGTH_SHORT
                        ).show()
                        //lanjut ke dashboard
                    }
            }
        }
    }
}