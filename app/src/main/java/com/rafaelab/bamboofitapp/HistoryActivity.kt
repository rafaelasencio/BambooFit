package com.rafaelab.bamboofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rafaelab.bamboofitapp.Adapter.HistoryAdapter
import com.rafaelab.bamboofitapp.Model.User
import com.rafaelab.bamboofitapp.utils.Constants
import kotlinx.android.synthetic.main.activity_history.*
import java.util.*
import kotlin.collections.ArrayList

class HistoryActivity : AppCompatActivity() {

    var alldates = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)
        val actionBar = supportActionBar

        if(actionBar != null){
            //set back button on actionbar
            actionBar.setDisplayHomeAsUpEnabled(true)
            //set title actionbar
            actionBar.title = "Historial"
        }

        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        Constants.DB_ROUTINES.child(Constants.UID)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    val values =  snapshot.value as Map<String, Any>
                    for ((_, value) in values) {
                        if (value != null) {
                            alldates.add(value as String)
                        }
                    }
                    getAllCompletedSessionsDate(alldates)
                }

            })


    }

    private fun getAllCompletedSessionsDate(routines: ArrayList<String>){
        //val dbHandler = SqliteOpenHelper(this, null)
        //val allDates = dbHandler.getCompletedSessionsDate()

        if (alldates.size > 0){
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataResult.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, alldates)
            rvHistory.adapter = historyAdapter
        } else {
            tvHistory.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tvNoDataResult.visibility = View.VISIBLE
        }
    }
}