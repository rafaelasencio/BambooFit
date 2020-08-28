package com.rafaelab.bamboofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

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

        getAllCompletedSessionsDate()
    }

    private fun getAllCompletedSessionsDate(){
        val dbHandler = SqliteOpenHelper(this, null)
        val allDates = dbHandler.getCompletedSessionsDate()

        if (allDates.size > 0){
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataResult.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, allDates)
            rvHistory.adapter = historyAdapter
        } else {
            tvHistory.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tvNoDataResult.visibility = View.VISIBLE
        }
    }

}
