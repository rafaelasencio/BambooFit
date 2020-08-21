package com.rafaelab.bamboofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}
