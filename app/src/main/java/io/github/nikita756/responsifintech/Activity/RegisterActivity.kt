package io.github.nikita756.responsifintech.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import io.github.nikita756.responsifintech.R
import io.github.nikita756.responsifintech.model.User
import io.github.nikita756.responsifintech.sql.DatabaseHelper

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val daftar = findViewById<Button>(R.id.appCompatButtonRegister)

        daftar.setOnClickListener {
            intent= Intent (this@RegisterActivity , DasboardActivity:: class.java)

            startActivity(intent)

        }
    }
}
