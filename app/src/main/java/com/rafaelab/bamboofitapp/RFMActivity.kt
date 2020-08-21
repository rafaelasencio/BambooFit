package com.rafaelab.bamboofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_imc.*
import java.math.BigDecimal
import java.math.RoundingMode
import android.widget.ToggleButton
import android.widget.RadioGroup



class RFMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        setSupportActionBar(toolbar_imc_activity)

        val actionBar = supportActionBar
        if(actionBar != null){
            //set back button on actionbar
            actionBar.setDisplayHomeAsUpEnabled(true)
            //set title actionbar
            actionBar.title = "Calculadora MGR"
        }

        toolbar_imc_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        llDiplayRFMResult.visibility = View.GONE
        btnCalculate.setOnClickListener {
            if(validateUnits()){
                val heightValue: Float = etHeight.text.toString().toFloat()
                val waistValue: Float = etWaist.text.toString().toFloat()

                var genderValue: Int = if(rbMen.isChecked) 64 else 76

                val mgr = genderValue - (20 * heightValue / waistValue)
                displayRFMResult(mgr)
            }else {
                Toast.makeText(this, "Por favor inserte valores válidos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayRFMResult(rfm: Float){

        val imcLabel: String
        val imcDescription: String

        if (rfm.compareTo(18f) <= 0) {
            imcLabel = "Peso insuficiente"
            imcDescription = "Necesitas cuidarte mejor. Come más!"
        } else if (rfm.compareTo(18.5f) > 0 && rfm.compareTo(24.99f) <= 0) {
            imcLabel = "Normopeso"
            imcDescription = "¡Felicidades! ¡Estás en buena forma!"
        } else if (rfm.compareTo(25f) > 0 && rfm.compareTo(26.99f) <= 0) {
            imcLabel = "Sobrepeso grado I"
            imcDescription = "Necesitas cuidarte un poco. \n Hacer ejercicio te vendría bien"
        } else if (rfm.compareTo(27f) > 0 && rfm.compareTo(29.99f) <= 0) {
            imcLabel = "Sobrepeso grado II (preobesidad)"
            imcDescription = "Necesitas cuidarte mejor y hacer más ejercicio"
        } else if (java.lang.Float.compare(rfm, 30f) > 0 && java.lang.Float.compare(rfm, 34.99f) <= 0) {
            imcLabel = "Obesidad de tipo I"
            imcDescription = "Realmente necesitas cuidarte más. \n Deberias de hacer ejercicio y mejorar tu alimentacion!"
        } else if (rfm.compareTo(35f) > 0 && rfm.compareTo(39.99f) <= 0) {
            imcLabel = "Obesidad de tipo II"
            imcDescription = "Estás en una condición de riesgo. \n Debes cuidar tu alimentacion y hacer ejercicio"
        } else if (rfm.compareTo(40f) > 0 && rfm.compareTo(49.99f) <= 0) {
            imcLabel = "Obesidad de tipo III (mórbida)"
            imcDescription = "Estás en una condición peligrosa. \n Debes cuidar tu alimentacion y hacer ejercicio!"
        } else {
            imcLabel = "Obesidad de tipo IV (extrema)"
            imcDescription = "Estás en una condición muy peligrosa. \n Deberías de acudir a un médico"
        }

        val imcValue = BigDecimal(rfm.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        tvRFMValue.text = imcValue
        tvRFMType.text = imcLabel
        tvRFMDescription.text = imcDescription
        llDiplayRFMResult.visibility = View.VISIBLE
    }


    private fun validateUnits(): Boolean {
        return etWaist.text.toString().isNotEmpty() &&
                etHeight.text.toString().isNotEmpty()
    }



}
