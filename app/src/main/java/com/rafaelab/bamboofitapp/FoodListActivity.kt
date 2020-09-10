package com.rafaelab.bamboofitapp

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_food_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class FoodListActivity : AppCompatActivity() {

    private var foodMenu = ArrayList<MenuModel>()
    private var foodAdapter: FoodListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_food_list)

        setSupportActionBar(toolbar_food_list_activity)

        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_food_list_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        getDietForCalories(1000)
        setupFoodListAdapter()

    }

    private fun setupFoodListAdapter(){
        rvFoodList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        foodAdapter = FoodListAdapter(foodMenu!!, this)
        rvFoodList.adapter = foodAdapter
    }


    fun getDietForCalories(calories: Int): ArrayList<MenuModel> {
        try {
            val fileInString: String = applicationContext.assets.open("foodList.json").bufferedReader().use {
                it.readText()
            }

            var jsonObjects = JSONObject(fileInString)
            var jsonDiet = jsonObjects.optJSONObject(calories.toString())

            val breakfastIngredients = getIngredientsList(jsonDiet, "desayuno")
            val brunchIngredients = getIngredientsList(jsonDiet, "media-mañana")
            val lunchIngredients = getIngredientsList(jsonDiet, "almuerzo")
            val breakIngredients = getIngredientsList(jsonDiet, "merienda")
            val dinnerIngredients = getIngredientsList(jsonDiet, "cena")

            val desayuno = MenuModel("Desayuno", breakfastIngredients, 100)
            val mediaMañana = MenuModel("Media Mañana", brunchIngredients, 100)
            val almuerzo = MenuModel("Almuerzo", lunchIngredients, 100)
            val merienda = MenuModel("Merienda", breakIngredients, 100)
            val cena = MenuModel("Cena", dinnerIngredients, 100)

            foodMenu.add(desayuno)
            foodMenu.add(mediaMañana)
            foodMenu.add(almuerzo)
            foodMenu.add(merienda)
            foodMenu.add(cena)

        }catch (e: IOException){

        }
        return foodMenu
    }

    fun getIngredientsList(mealObject: JSONObject, mealName: String): List<String>{
        var ingredientList = mutableListOf<String>()
        val meal = mealObject.optJSONArray(mealName)

        for(a in 0 until meal.length()){
            ingredientList.add(meal.get(a).toString())
        }
        return ingredientList
    }


}