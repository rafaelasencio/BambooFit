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

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        txtEmail = findViewById(R.id.etEmail)
        progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }

    fun sendPassword(view: View){
        val email = txtEmail.text.toString()

        if(!TextUtils.isEmpty(email)){
            progressBar.visibility = View.VISIBLE
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Compruebe su correo", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }else{
                    Toast.makeText(this, "Error al enviar el email",
                        Toast.LENGTH_SHORT).show()
                }
                progressBar.visibility = View.GONE
            }
        }
    }
}