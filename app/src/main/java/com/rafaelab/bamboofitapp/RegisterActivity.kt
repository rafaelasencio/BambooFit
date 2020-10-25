package com.rafaelab.bamboofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rafaelab.bamboofitapp.Model.User

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
        dbReference = database.reference.child("users")
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

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    if (!it.isSuccessful){
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "error al crear el usuario", Toast.LENGTH_SHORT).show()
                        return@addOnCompleteListener
                    }
                    if (it.isSuccessful){
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "user created successfully", Toast.LENGTH_SHORT).show()
                        val uid = FirebaseAuth.getInstance().uid ?: ""
                        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
                        val username =
                            name.toLowerCase().replace("\\s".toRegex(),"") + "_" +
                                    lastName.toLowerCase().replace("\\s".toRegex(), "")
                        val user = User(uid, email, username,"","")
                        ref.setValue(user)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Changes updated successfully", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Something was wrong", Toast.LENGTH_SHORT).show()
                            }
                        /*
                        val user:FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user!!.uid)
                        userBD.child("name").setValue(name)
                        userBD.child("lastName").setValue(lastName)

                         */

                        action()
                    }
            }
                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun action(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?){
         user?.sendEmailVerification()?.addOnCompleteListener(this) {
             if(it.isSuccessful){
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
