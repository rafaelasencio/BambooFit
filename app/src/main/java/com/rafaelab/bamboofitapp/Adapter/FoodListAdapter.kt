package com.rafaelab.bamboofitapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelab.bamboofitapp.MenuModel
import com.rafaelab.bamboofitapp.R
import kotlinx.android.synthetic.main.item_food_list.view.*

class FoodListAdapter(val items: ArrayList<MenuModel>, val context: Context) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMealName = view.tvMealDay
        val tvIngredients = view.tvIngredients
        val tvKcal = view.tvKcal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_food_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: MenuModel = items[position]
        val mealName = model.getMeal()
        val ingredients = model.getIngredientList(model.getIngredients())
        val calories = model.getCalories().toString()

        holder.tvMealName.text = mealName
        holder.tvIngredients.text = ingredients
        holder.tvKcal.text = calories
    }

}