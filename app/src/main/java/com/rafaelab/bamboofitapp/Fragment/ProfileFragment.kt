package com.rafaelab.bamboofitapp.Fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.rafaelab.bamboofitapp.LoginActivity
import com.rafaelab.bamboofitapp.Model.User
import com.rafaelab.bamboofitapp.R
import com.rafaelab.bamboofitapp.utils.Constants.Companion.hideKeyboard
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    internal var user: User? = null
    internal var selectedPhotoURI: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_profile, container, false)
        val uid = FirebaseAuth.getInstance().uid ?: ""

        setUpdateButton(false, view)

        loadUserProfileData(uid)

        view.ll_profile.setOnClickListener {
            hideKeyboard(view.context)
        }

        view.ed_userPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (user != null) {
                    if (user!!.telephone != s.toString()) {
                        setUpdateButton(true, view)
                    } else {
                        setUpdateButton(false, view)
                    }
                }
            }
        })

        view.ed_userName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (user != null) {
                    if (user!!.username != s.toString()) {
                        setUpdateButton(true, view)
                    } else {
                        setUpdateButton(false, view)
                    }
                }
            }
        })


        view.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        view.iv_userImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        view.btnUpdate.setOnClickListener {
            uploadDataToFirebase()
        }
        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 &&  resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoURI = data.data
            setUpdateButton(true, view!!)
            val bitmap = MediaStore.Images.Media.getBitmap(view!!.context.contentResolver, selectedPhotoURI)
            view!!.iv_userImage.setImageBitmap(bitmap)
        }
    }

    private fun uploadDataToFirebase(){
        if(selectedPhotoURI != null) {
            uploadPhotoToFirebase()
        } else if (user != null) {
            updateUserToFirebaseDatabase(user!!.profileImageUrl!!)
        }
    }

    private fun uploadPhotoToFirebase(){
            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            ref.putFile(selectedPhotoURI!!).addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    updateUserToFirebaseDatabase(it.toString())
                }
            }
                .addOnFailureListener{
                    //manage failure
                    Toast.makeText(context, "Error al subir la foto", Toast.LENGTH_SHORT).show()
                }
    }

    private fun updateUserToFirebaseDatabase(profileImageURL: String){
        //actualizar solo campos necesarios. Evitar eliminar routines al actualizar
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        val user = User(
            uid,
            ed_userEmail.text.toString(),
            ed_userName.text.toString(),
            ed_userPhone.text.toString(),
            profileImageURL)//User(uid, ed_userName.text.toString(), ed_userEmail.text.toString(), profileImageURL)
        ref.setValue(user).addOnSuccessListener {
            Toast.makeText(context, "Información actualizada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserProfileData(uuid: String){
        FirebaseDatabase.getInstance().reference.child("users").child(uuid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    var value = snapshot.value as Map<String, Any>
                    Log.i("snapshot",""+value)
                    val uid = value["uid"].toString()
                    val username = value["username"].toString()
                    val userEmail = value["userEmail"].toString()
                    val userPhone = value["telephone"].toString()
                    val profileImageUrl = value["profileImageUrl"].toString()
                    view!!.ed_userName.setText(username)
                    view!!.ed_userEmail.setText(userEmail)
                    view!!.ed_userPhone.setText(userPhone)
                    if (profileImageUrl!=""){
                        Picasso.get().load(profileImageUrl).into(iv_userImage)
                    }
                    user = User(uid, userEmail, username, userPhone, profileImageUrl)
                }
            })
    }

    private fun setUpdateButton(status: Boolean, view: View){
        view.btnUpdate.isEnabled = status
        view.btnUpdate.isClickable = status
        if (status) {
            view.btnUpdate.setTextColor(resources.getColor(R.color.colorWhite))
            view.btnUpdate.setBackgroundColor(resources.getColor(R.color.colorAccent))
        } else {
            view.btnUpdate.setTextColor(resources.getColor(R.color.colorBlack))
            view.btnUpdate.setBackgroundColor(resources.getColor(R.color.colorHighLighted))
        }
    }


}