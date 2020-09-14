package com.rafaelab.bamboofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var txtUsername: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUsername = findViewById(R.id.etUser)
        txtPassword = findViewById(R.id.etPassword)
        progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }

    fun forgotPassword(view: View){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }

    fun goToRegisterUserActivity(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun login(view: View){
        loginUser()
    }

    private fun loginUser(){
        val user: String = txtUsername.text.toString()
        val password: String = txtPassword.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    action()
                }else{
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Error en la autenticación", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}