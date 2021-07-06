package io.github.nikita756.responsifintech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regemail=findViewById<TextView>(R.id.regisemail)
        val regpass = findViewById<TextView>(R.id.regissandi)
        val konfirpass = findViewById<TextView>(R.id.regiskonsandi)
        val daftar = findViewById<Button>(R.id.daftar)

        daftar.setOnClickListener {
            intent= Intent (this@RegisterActivity , DasboardActivity:: class.java)

            startActivity(intent)

        }
    }
}