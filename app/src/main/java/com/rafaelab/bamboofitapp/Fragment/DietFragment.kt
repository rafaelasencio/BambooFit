package com.rafaelab.bamboofitapp.Fragment


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.rafaelab.bamboofitapp.DietActivity
import com.rafaelab.bamboofitapp.FoodListActivity

import com.rafaelab.bamboofitapp.R
import kotlinx.android.synthetic.main.activity_diet.*
import kotlinx.android.synthetic.main.dialog_calories_result.*
import kotlinx.android.synthetic.main.fragment_diet.view.*
import kotlinx.android.synthetic.main.fragment_diet.view.etAge
import kotlinx.android.synthetic.main.fragment_diet.view.etHeight2
import kotlinx.android.synthetic.main.fragment_diet.view.etWeight
import kotlinx.android.synthetic.main.fragment_diet.view.rbAthleteActivity
import kotlinx.android.synthetic.main.fragment_diet.view.rbEliteActivity
import kotlinx.android.synthetic.main.fragment_diet.view.rbGain
import kotlinx.android.synthetic.main.fragment_diet.view.rbLightActivity
import kotlinx.android.synthetic.main.fragment_diet.view.rbMale
import kotlinx.android.synthetic.main.fragment_diet.view.rbModerateActivity
import kotlinx.android.synthetic.main.fragment_diet.view.rbNoActivity
import kotlinx.android.synthetic.main.fragment_diet.view.rbThin

/**
 * A simple [Fragment] subclass.
 */
class DietFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_diet, container, false)


        view.btnCalculateCalories.setOnClickListener {
            if(validateUnits()){
                calculateTotalCalories()
            }else{
                Toast.makeText(activity, "Por favor asegurese de rellenar todos los campos con valores válidos",
                    Toast.LENGTH_SHORT).show()
            }
        }
        return view
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
        val customDialog = Dialog(context!!)
        customDialog.setContentView(R.layout.dialog_calories_result)
        customDialog.tvCalories.text = calories.toString()
        if (calories >= 1000 && calories <= 5000) {
            customDialog.btnGenerateDiet.visibility = View.VISIBLE
        } else {
            customDialog.btnGenerateDiet.visibility = View.GONE
        }
        customDialog.tv_exit.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.btnGenerateDiet.setOnClickListener {
            val intent = Intent(activity, FoodListActivity::class.java)
            intent.putExtra("calories_key", calories)
            startActivity(intent)
            customDialog.dismiss()
        }
        customDialog.show()
    }

}
