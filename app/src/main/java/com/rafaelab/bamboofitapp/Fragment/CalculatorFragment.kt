package com.rafaelab.bamboofitapp.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.rafaelab.bamboofitapp.R
import com.rafaelab.bamboofitapp.RFMActivity
import com.rafaelab.bamboofitapp.StartExerciseActivity
import kotlinx.android.synthetic.main.activity_imc.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*
import kotlinx.android.synthetic.main.fragment_calculator.view.btnCalculate
import kotlinx.android.synthetic.main.fragment_calculator.view.etHeight
import kotlinx.android.synthetic.main.fragment_calculator.view.etWaist
import kotlinx.android.synthetic.main.fragment_calculator.view.rbMen
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * A simple [Fragment] subclass.
 */
class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_calculator, container, false)

        view.llDiplayRFMResult.visibility = View.GONE
        view.btnCalculate.setOnClickListener {
            if(validateUnits()){
                val heightValue: Float = etHeight.text.toString().toFloat()
                val waistValue: Float = etWaist.text.toString().toFloat()

                var genderValue: Int = if(rbMen.isChecked) 64 else 76

                val mgr = genderValue - (20 * heightValue / waistValue)
                displayRFMResult(mgr)
            }else {
                Toast.makeText(activity, "Por favor inserte valores válidos",
                    Toast.LENGTH_SHORT).show()
            }
        }
        return view
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
