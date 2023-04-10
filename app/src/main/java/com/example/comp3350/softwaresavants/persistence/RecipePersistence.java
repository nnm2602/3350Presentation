
package com.example.comp3350.softwaresavants.persistence;
import com.example.comp3350.softwaresavants.Objects.Recipe;

import java.util.List;

public interface RecipePersistence {
    // get a recipe that is has its vegCount <= numVegetables and numCount <= numProteins
    // ideally vegCount == numVegetables and numCount == numProteins
    public Recipe getRecipeFrame(int numVegetables, int numProteins);

    //return the recipe by name, name is distinct in this db
    public Recipe getRecipeByName( String recipeName);

    // add a recipe to the database, name has to be distinct
    public void addRecipe(Recipe recipe);

    //remove a recipe given its name
    public void removeRecipe(String recipeName);

    //get all recipe
    public List<Recipe> getAllRecipe();

}
