package com.rafaelab.bamboofitapp.Model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

class Userr(snapshot: DataSnapshot) {
    lateinit var uid: String
    lateinit var username: String
    lateinit var userEmail: String
    var profileImageUrl: String? = null

    init {
        try {
            val data: HashMap<String, Any> = snapshot.value as HashMap<String, Any>
            data["uid"] as String
            data["username"] as String
            data["userEmail"] as String
            data["profileImageUrl"] as String

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@IgnoreExtraProperties
data class User(
    var uid: String? = "",
    var userEmail: String? = "",
    var username: String? = "",
    var telephone: String? = "",
    var profileImageUrl: String? = "",
    var routines: MutableList<String>? = mutableListOf()
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "userEmail" to userEmail,
            "username" to username,
            "telephone" to telephone,
            "profileImageUrl" to profileImageUrl,
            "routines" to routines
        )
    }
}