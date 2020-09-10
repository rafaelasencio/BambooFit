package com.rafaelab.bamboofitapp

import org.json.JSONObject

class MenuModel(private var meal:String,
                private var ingredients: List<String>,
                private var calories: Int) {


    fun getMeal(): String{
        return meal
    }

    fun setMeal(meal: String){
        this.meal = meal
    }

    fun getIngredients(): List<String>{
        return ingredients
    }

    fun setIngredients(description: List<String>){
        this.ingredients = description
    }

    fun getCalories(): Int{
        return calories
    }

    fun setCalories(calories: Int){
        this.calories = calories
    }

    fun getIngredientList(ingredients: List<String>): String {
        var ingrediente = ""
        for (ingredient in ingredients){
            ingrediente += ingredient + System.lineSeparator()
        }
        return ingrediente
    }

}