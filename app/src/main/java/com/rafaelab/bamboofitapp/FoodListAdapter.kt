package com.rafaelab.bamboofitapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food_list.view.*
import kotlinx.android.synthetic.main.item_history_row.view.*

class FoodListAdapter(val items: ArrayList<FoodModel>, val context: Context) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFoodName = view.tvFoodName
        val tvIngredients = view.tvIngredients
        val tvKcal = view.tvKcal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.item_food_list, parent, false))
    }

    override fun getItemCount(): Int {
        Log.e("total", items.size.toString())
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: FoodModel = items[position]
        holder.tvFoodName.text = model.getFoodTitle()
        holder.tvIngredients.text = model.getIngredients().toString()
        holder.tvKcal.text = model.getKcal().toString()
        //val item = items.get(position)
        //holder..text = "A"//item.getFoodTitle()
        //holder.tvIngredients.text = "B"//item.getIngredients().toString()
        //holder.tvKcal.text = "C"//item.getKcal().toString()

        //Picasso.get().load()
    }

}