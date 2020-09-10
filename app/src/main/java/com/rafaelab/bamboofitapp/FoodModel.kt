package com.rafaelab.bamboofitapp

class FoodModel(private var id: Int,
                private var foodTitle: String,
                private var kcal: Int,
                private var ingredients: List<String>) {

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getFoodTitle(): String {
        return foodTitle
    }

    fun setFoodTitle(foodTitle: String){
        this.foodTitle = foodTitle
    }

    fun getKcal():Int{
        return kcal
    }

    fun setKcal(kcal: Int){
        this.kcal = kcal
    }

    fun getIngredientsList():List<String>{
        return ingredients
    }

    fun setIngredientsList(ingredients: List<String>){
        this.ingredients = ingredients
    }


}