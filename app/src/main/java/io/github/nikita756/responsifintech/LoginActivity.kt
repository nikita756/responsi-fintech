package io.github.nikita756.responsifintech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.github.nikita756.responsifintech.Helper.InputValidation

class LoginActivity : AppCompatActivity() {

    private lateinit var inputValidation: InputValidation

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_Login)
        val email = findViewById<EditText>(R.id.email)
        val sandi = findViewById<EditText>(R.id.katasandi)
        val masuk = findViewById<Button>(R.id.masuk)
        val lupa =findViewById<TextView>(R.id.lupasandi)
        val buat=findViewById<TextView>(R.id.buatakun)


        initObjects()

        masuk.setOnClickListener {
val email =  email.text.toString()
            val katasandi= sandi.text.toString()

            val intent = Intent (this@LoginActivity, DasboardActivity::class.java)
            intent.putExtra ("Nama Pengguna", email)
            intent.putExtra ("Kata Sandi", katasandi)
            startActivity(intent)
    }
        lupa.setOnClickListener {
            val intent =Intent (this@LoginActivity, RecoveryActivity::class.java)

            startActivity(intent)

        }

        buat.setOnClickListener {
            val intent = Intent (this@LoginActivity, RegisterActivity:: class.java)

            startActivity(intent)
        }
}
    private fun initObjects() {
        inputValidation = InputValidation(this@LoginActivity)
    }

}