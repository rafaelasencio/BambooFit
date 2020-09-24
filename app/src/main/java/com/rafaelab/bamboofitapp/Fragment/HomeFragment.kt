package com.rafaelab.bamboofitapp.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafaelab.bamboofitapp.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.llDiet
import kotlinx.android.synthetic.main.fragment_home.view.llHistory

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
        view.llExercise.setOnClickListener {

            val intent = Intent(
                activity, StartExerciseActivity::class.java)
            startActivity(intent)
        }

        view.llRFM.setOnClickListener {
            val intent = Intent(
                activity, RFMActivity::class.java)

            startActivity(intent)
        }

        view.llHistory.setOnClickListener{
            val intent = Intent(
                activity, HistoryActivity::class.java)
            startActivity(intent)
        }

        view.llDiet.setOnClickListener {
            //DietActivity
            val intent = Intent(
                activity, DietActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
