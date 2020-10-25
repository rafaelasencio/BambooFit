package com.rafaelab.bamboofitapp.Adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rafaelab.bamboofitapp.Model.User
import com.rafaelab.bamboofitapp.R
import kotlinx.android.synthetic.main.item_history_row.view.*

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistoryMainItem = view.ll_history_item_main
        val tvItem = view.tvItemHistory
        val tvPosition = view.tvPosition
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.item_history_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)
        Log.i("dateitem",""+date)
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        val color: String = if(position%2==0) "#EBEBEB" else "#FFFFFF"
        holder.llHistoryMainItem.setBackgroundColor(Color.parseColor(color))
    }

}


