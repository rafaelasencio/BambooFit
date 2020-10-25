package com.rafaelab.bamboofitapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.rafaelab.bamboofitapp.utils.Constants
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(toolbar_finish_activity)

        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnFinish.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        addDateToDatabase()
    }

    private fun addDateToDatabase(){
        val calendary = Calendar.getInstance()
        val dateTime = calendary.time

        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = dateFormat.format(dateTime)

        Log.e("Formatted Date : ", "" + date)
        val routine: MutableMap<String, Any> = mutableMapOf()
        routine.plus(Pair("date",date))
        Constants.DB_ROUTINES.child(Constants.UID).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                Constants.DB_ROUTINES.child(Constants.UID).push().setValue(date)
            }

        })

        //val dbHandler = SqliteOpenHelper(this, null)
        //dbHandler.addDate(date)
        //Log.e("Date : ", "Added...")
    }


}

@IgnoreExtraProperties
data class Routine(var date: String? = null) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "date" to date
        )
    }
}
