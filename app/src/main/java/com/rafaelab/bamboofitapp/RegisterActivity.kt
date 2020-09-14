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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName = findViewById(R.id.etName)
        txtLastName = findViewById(R.id.etLastName)
        txtEmail = findViewById(R.id.etEmail)
        txtPassword = findViewById(R.id.etPassword)

        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        dbReference = database.reference.child("User")
    }

    fun registerUser(view: View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name: String = txtName.text.toString()
        val lastName: String = txtLastName.text.toString()
        val email: String = txtEmail.text.toString()
        val password: String = txtPassword.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) &&
            !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(email, password )
                .addOnCompleteListener(this){
                task ->

                if(task.isSuccessful){
                    val user:FirebaseUser? = auth.currentUser
                    verifyEmail(user)

                    val userBD = dbReference.child(user!!.uid)
                    userBD.child("Name").setValue(name)
                    userBD.child("lastName").setValue(lastName)
                    action()
                }else{
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "error al crear el usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?){
         user?.sendEmailVerification()?.addOnCompleteListener(this) {
             task ->
             if(task.isSuccessful){
                 Toast.makeText(this, "email enviado", Toast.LENGTH_SHORT).show()
             }else{
                 progressBar.visibility = View.GONE
                 Toast.makeText(this, "error al enviar el email", Toast.LENGTH_SHORT).show()
             }
         }
    }

    fun goToLoginActivity(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
