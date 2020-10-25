package com.rafaelab.bamboofitapp.Model

import android.util.Log
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

object UserModel: Observable() {
/*
    private var mValueDataListener: ValueEventListener? = null
    private var mUserList: ArrayList<User>? = ArrayList()

    private fun getDatabaseRef(): DatabaseReference? {
        return FirebaseDatabase.getInstance().reference.child("users")
    }

    init {
        if (mValueDataListener != null) {
            getDatabaseRef()?.removeEventListener(mValueDataListener!!)
        }
        mValueDataListener = null
        Log.i("","")
        mValueDataListener = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                if (error != null) {
                    Log.i("error", "data update cancelled, error: ${error.message}")
                }
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    Log.i("user", "data updated for user")
                    val data: ArrayList<User> = ArrayList()
                    if (snapshot != null){
                        for (snapshot: DataSnapshot in snapshot.children) {
                            try {
                                data.add(User(snapshot))
                            }catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        mUserList = data
                        Log.i("usermodel","data updated")
                    }
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        getDatabaseRef()?.addValueEventListener(mValueDataListener!!)
    }
    fun getData(): ArrayList<User>?{
        return mUserList
    }

 */
}