package com.example.comp3350.softwaresavants.persistence;

import com.example.comp3350.softwaresavants.Objects.Ingredient;

import java.util.List;

public interface IngredientPersistence {

    // return the list of all ingredient
    public List<Ingredient> getAllIngredient();

    // return the list all of ingredient that is of type vegetable
    public List<Ingredient> getAllVegetables();

    // return the list all of ingredient that is of type protein
    public List<Ingredient> getAllProteins();

    // return the ingredient by name
    public Ingredient getIngredientByName(String name);

    // add an ingredient into the DB
    public void addIngredient(Ingredient ingredient);

    // remove an ingredient from the DB
    public void removeIngredient(String name);
}
