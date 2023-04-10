package com.example.comp3350.softwaresavants.Objects;

public class Ingredient {

    private String ingredientName; //the name of the food

    private String type; // the type of the food (either vegetable or protein)

    
    //constructor==================================================================
    public Ingredient(String ingredientName, String type){
        this.ingredientName = ingredientName;
        this.type = type;
    }

    //=============================================================================

    //method

    public String getIngredientName() {
        return this.ingredientName;
    }
    public String getType() {
        return this.type;
    }
}

