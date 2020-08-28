package com.rafaelab.bamboofitapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diet.*
import kotlinx.android.synthetic.main.dialog_calories_result.*

class DietActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        setSupportActionBar(toolbar_diet_activity)

        val actionbar = supportActionBar

        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "Dieta"
        }

        toolbar_diet_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnCalculateCalories.setOnClickListener {
            if(validateUnits()){
                calculateTotalCalories()
            }else{
                Toast.makeText(this, "Por favor asegurese de rellenar todos los campos con valores válidos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateTotalCalories(){

        var TMBEquationValue = 0f
        val weight: Float = etWeight.text.toString().toFloat()
        val height: Float = etHeight2.text.toString().toFloat()
        val age = etAge.text.toString().toInt()

        if(rbMale.isChecked){
            TMBEquationValue = 66f + (13.7f * weight) + (5f * height) - (6.75f * age.toFloat())
        } else {
            TMBEquationValue = 665f + (9.6f * weight) + (1.8f * height) - (4.7f * age.toFloat())
        }

        val dailyCalories = caloriesForDailyActivity(TMBEquationValue)
        val caloriesResult = setTotalCaloriesForPurpose(dailyCalories)
        customDialogForCalories(caloriesResult)
    }

    private fun caloriesForDailyActivity(calories: Float): Float {
        var total = 0f

        if(rbNoActivity.isChecked){
            total = calories * 1.2f
        } else if (rbLightActivity.isChecked){
            total = calories * 1.375f
        } else if (rbModerateActivity.isChecked){
            total = calories * 1.55f
        } else if (rbAthleteActivity.isChecked){
            total = calories * 1.72f
        } else if (rbEliteActivity.isChecked){
            total = calories * 1.9f
        }
        return total
    }

    private fun setTotalCaloriesForPurpose(calories: Float): Int{

        var extraCalories = 0

        if(rbThin.isChecked){
            extraCalories = -400
        } else if(rbGain.isChecked){
            extraCalories = if(rbMale.isChecked) 400 else 200
        } else {
            extraCalories = 0
        }
        return calories.toInt() + extraCalories
    }

    private fun validateUnits(): Boolean {
        return etHeight2.text.toString().isNotEmpty() &&
                etWeight.text.toString().isNotEmpty() &&
                etAge.text.toString().isNotEmpty()
    }

    private fun customDialogForCalories(calories: Int){
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.dialog_calories_result)
        customDialog.tvCalories.text = calories.toString()

        customDialog.btnContinue.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

}
