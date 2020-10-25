package com.rafaelab.bamboofitapp.Fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.rafaelab.bamboofitapp.*
import com.rafaelab.bamboofitapp.utils.Constants

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start_exercise.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.rbBeginner
import kotlinx.android.synthetic.main.fragment_home.rbMiddle
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        view.llStartExercise.setOnClickListener {
            val intent = Intent(
                activity, ExerciseActivity::class.java)
            intent.putExtra("level_key", getExerciseLevel())
            startActivity(intent)
        }

        mViewPager

        view.tvSavedRoutines.setOnClickListener {
            val intent = Intent(
                activity, HistoryActivity::class.java)
            startActivity(intent)
        }


        return view
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
