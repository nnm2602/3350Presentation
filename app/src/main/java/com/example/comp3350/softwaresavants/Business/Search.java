package com.example.comp3350.softwaresavants.Business;


import android.util.Log;

import java.util.List;


import com.example.comp3350.softwaresavants.Objects.Ingredient;
import com.example.comp3350.softwaresavants.Objects.Recipe;
import com.example.comp3350.softwaresavants.application.Services;
import com.example.comp3350.softwaresavants.persistence.IngredientPersistence;
import com.example.comp3350.softwaresavants.persistence.RecipePersistence;

public class Search {
    private RecipePersistence recipeDatabase;

    public Search() {
        recipeDatabase = Services.getRecipePersistence();
    }

    public Search(RecipePersistence recipeDatabase) {
        this.recipeDatabase = recipeDatabase;
    }

    public String findRecipe(List<String> vegetables, List<String> proteins) {
        if (vegetables == null || proteins == null) {
            return "Input lists cannot be null.";
        }
        int numVegetables = vegetables.size();
        int numProteins = proteins.size();
        String recipeFrame = recipeDatabase.getRecipeFrame(numVegetables, numProteins).getTutorial();
        if (recipeFrame == null) {
            return "Not enough ingredients to generate recipe.";
        }
        int vegPlaceholderCount = countOccurrences(recipeFrame, "[vegetable");
        int proteinPlaceholderCount = countOccurrences(recipeFrame, "[protein");
        if (vegPlaceholderCount <= numVegetables && proteinPlaceholderCount <= numProteins) {
            List<String> vegSublist = vegetables;
            if (vegPlaceholderCount < numVegetables) {
                vegSublist = vegetables.subList(0, vegPlaceholderCount);
            }
            List<String> proteinSublist = proteins;
            if (proteinPlaceholderCount < numProteins) {
                proteinSublist = proteins.subList(0, proteinPlaceholderCount);
            }
            return fillPlaceholders(recipeFrame, vegSublist, proteinSublist);
        } else {
            return "Not enough ingredients to generate recipe.";
        }
    }

    private static int countOccurrences(String str, String sub) {
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    private static String fillPlaceholders(String frame, List<String> vegIngredients, List<String> proteinIngredients) {
        String recipe = frame;
        for (int i = 1; i <= vegIngredients.size(); i++) {
            recipe = recipe.replace("[vegetable" + i + "]", vegIngredients.get(i - 1));
        }
        for (int i = 1; i <= proteinIngredients.size(); i++) {
            recipe = recipe.replace("[protein" + i + "]", proteinIngredients.get(i - 1));
        }
        return recipe;
    }
}
