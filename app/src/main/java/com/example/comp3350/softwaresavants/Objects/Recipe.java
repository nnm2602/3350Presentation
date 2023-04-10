package com.example.comp3350.softwaresavants.Objects;

public class Recipe {

private String recipeName; //name of the recipe.
private String tutorial; // tell user how to cook.

private int vegCount;

private int proteinCount;

//Consturctor==============================================================

public Recipe(String name, String tutorial, int vegCount, int proteinCount) {
    this.recipeName = name;
    this.tutorial = tutorial;
    this.vegCount = vegCount;
    this.proteinCount = proteinCount;

}

//=========================================================================

//method

public String getRecipeName() {
    return recipeName;
}

public String getTutorial() {
    return tutorial;
}

public int getVegCount() {return vegCount;}

public int getProteinCount(){return proteinCount;}

//change recipes name;
public void setName(String name){
    recipeName = name;
}

//change recipes tutorial
public void setTutorial(String tutorial) {this.tutorial = tutorial; }
}

