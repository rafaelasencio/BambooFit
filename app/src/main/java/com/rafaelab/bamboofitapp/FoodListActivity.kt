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

    private var foodList = ArrayList<FoodModel>()
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

/*
        val fm1 = FoodModel(1, "AAA", 98, ArrayList<String>())
        foodList.add(fm1)
        val fm2 = FoodModel(2, "BBB", 70, ArrayList<String>())
        foodList.add(fm2)


 */


        //readJSON()

        //setupFoodListAdapter()

        readJSON2()
    }

    private fun setupFoodListAdapter(){
        rvFoodList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        foodAdapter = FoodListAdapter(foodList!!, this)
        rvFoodList.adapter = foodAdapter
    }

    fun readJSON(){
        try {
            val fileInString: String = applicationContext.assets.open("foodList.json").bufferedReader().use {
                it.readText()
            }

            var jsonArray = JSONArray(fileInString)

            val dieta = jsonArray.getJSONObject(1)
            for (i in 0..jsonArray.length()-1){
                val item = jsonArray.getJSONObject(i)

                val id = item.getString("id")
                val title = item.getString("title")
                val kcal = item.getString("kcal")
                val ingredients = item.getString("ingredients")

                val fm = FoodModel(id.toInt(), title, kcal.toInt(), ingredients)
                foodList.add(fm)
            }

        }catch (e: IOException){

        }

    }

    fun readJSON2(){
        try {
            val fileInString: String = applicationContext.assets.open("foodList.json").bufferedReader().use {
                it.readText()
            }
            var jsonObjects = JSONObject(fileInString)
            var calories = jsonObjects.getJSONArray("calorias")
            
            (0 until calories.length())
            println(calories)
            Log.e("kcal", calories.length().toString())

        }catch (e: IOException){

        }
    }


}