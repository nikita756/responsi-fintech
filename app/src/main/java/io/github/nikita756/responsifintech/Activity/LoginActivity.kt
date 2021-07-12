package io.github.nikita756.responsifintech.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.github.nikita756.responsifintech.Helper.InputValidation
import io.github.nikita756.responsifintech.R
import io.github.nikita756.responsifintech.sql.DatabaseHelper

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var activity = this@LoginActivity
    private lateinit var ConstraintLayout:ConstraintLayout
    private lateinit var textInputLayoutEmail :TextInputLayout
    private lateinit var textInputLayoutPassword : TextInputLayout
    private lateinit var inputValidation: InputValidation
    private lateinit var appCompatButtonLogin: AppCompatButton
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textViewLinkRegister: AppCompatTextView
    private lateinit var textViewLinkRecovery: AppCompatTextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // hiding the action bar
        supportActionBar!!.hide()
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()
        // initializing the objects
        initObjects()
    }
    /**
     * This method is to initialize views
     */
    private fun initViews() {
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as
                TextInputLayout
        textInputLayoutPassword = findViewById<View>(R.id.textInputLayoutPassword) as
                TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as
                TextInputLayout
        textInputLayoutPassword = findViewById<View>(R.id.textInputLayoutPassword) as
                TextInputLayout
        textInputEditTextEmail = findViewById<View>(R.id.textInputEditTextEmail) as
                TextInputEditText
        textInputEditTextPassword = findViewById<View>(R.id.textInputEditTextPassword) as
                TextInputEditText
        appCompatButtonLogin = findViewById<View>(R.id.appCompatButtonLogin) as
                AppCompatButton
        textViewLinkRegister = findViewById<View>(R.id.textViewLinkRegister) as
                AppCompatTextView
        textViewLinkRecovery=findViewById(R.id.textViewLinkRecovery) as
                AppCompatTextView
    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonLogin!!.setOnClickListener(this)
        textViewLinkRegister!!.setOnClickListener(this)
        textViewLinkRecovery!!.setOnClickListener(this)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.textViewLinkRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
            R.id.textViewLinkRecovery -> {
                val intentRecovery=Intent(applicationContext, RecoveryActivity::class.java)
                startActivity(intentRecovery)
            }
        }
    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
    elds and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
            if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!,
                            textInputLayoutEmail!!,"Email Anda Salah")) {
                return
            }

        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail!!,
                        textInputLayoutEmail!!,"Email Anda Salah")) {
            return
             }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword!!,
                        textInputLayoutPassword!!,"Password Anda Salah")) {
            return
        }
        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim { it <= ' ' },
                        textInputEditTextPassword!!.text.toString().trim { it <= ' ' }))
        {
            val accountsIntent = Intent(activity, DasboardActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        }

        else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(ConstraintLayout!!, "Email dan Password Anda Salah",
                    Snackbar.LENGTH_LONG).show()
        }
    }
    private fun emptyInputEditText() {
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
    }

}

