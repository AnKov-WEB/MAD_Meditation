package ru.kovalev_andrey.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRePassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.nameInputField)
        etEmail = findViewById(R.id.emailInputField)
        etPassword = findViewById(R.id.passwordInputField)
        etRePassword = findViewById(R.id.repeatPasswordInputField)
        btnSignUp = findViewById(R.id.regButton)
        btnRegister = findViewById(R.id.logButton)

        firebaseAuth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()
        val confirmPass = etRePassword.text.toString()

        if(name.isEmpty()) {
            etName.error = "Введите имя"
        }

        if(email.isEmpty()) {
            etEmail.error = "Введите Email"
        }

        if(pass.isEmpty()) {
            etPassword.error = "Введите пароль"
        }

        if(confirmPass.isEmpty()) {
            etRePassword.error = "Введите пароль"
        }

        if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        etEmail.error = "Некорректная форма"
                    }
                }
            } else {
                etRePassword.error = "Пароли не совпадают!"
            }
        }
    }
}