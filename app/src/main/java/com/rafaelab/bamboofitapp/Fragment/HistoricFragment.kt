package com.rafaelab.bamboofitapp.Fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafaelab.bamboofitapp.Adapter.HistoryAdapter
import com.rafaelab.bamboofitapp.DietActivity
import com.rafaelab.bamboofitapp.HistoryActivity

import com.rafaelab.bamboofitapp.R
import com.rafaelab.bamboofitapp.SqliteOpenHelper
import kotlinx.android.synthetic.main.fragment_historial.*

/**
 * A simple [Fragment] subclass.
 */
class HistoricFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_historial, container, false)
        //getAllCompletedSessionsDate()
        return view
    }

    private fun getAllCompletedSessionsDate(){
        val dbHandler = SqliteOpenHelper(view!!.context, null)
        val allDates = dbHandler.getCompletedSessionsDate()

        if (allDates.size > 0){
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tvNoDataResult.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(view!!.context)
            val historyAdapter = HistoryAdapter(view!!.context, allDates)
            rvHistory.adapter = historyAdapter
        } else {
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.GONE
            tvNoDataResult.visibility = View.VISIBLE
        }
    }


}
