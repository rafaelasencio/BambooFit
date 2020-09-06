package com.rafaelab.bamboofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llExercise.setOnClickListener {

            val intent = Intent(
                this, StartExerciseActivity::class.java)

            startActivity(intent)
        }

        llRFM.setOnClickListener {
            val intent = Intent(
                this, RFMActivity::class.java)

            startActivity(intent)
        }

        llHistory.setOnClickListener{
            val intent = Intent(
                this, HistoryActivity::class.java)
            startActivity(intent)
        }

        llDiet.setOnClickListener {
            //DietActivity
            val intent = Intent(
                this, FoodListActivity::class.java)
            startActivity(intent)
        }




    }
}
