package com.rafaelab.bamboofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_exercise.*

class StartExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_exercise)

        setSupportActionBar(toolbar_start_exercise_activity)

        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_start_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        llStartExercise.setOnClickListener {
            val intent = Intent(
                this, ExerciseActivity::class.java)
            intent.putExtra("level_key", getExerciseLevel())

            startActivity(intent)
        }

        tvOwnRoutine.setOnClickListener {
            //Pasar a la configuracion de la rutina

        }

    }

    private fun getExerciseLevel(): Int {
        if(rbBeginner.isChecked) {
            return 0
        } else if (rbMiddle.isChecked){
            return 1
        } else {
            return 2
        }
    }
}
