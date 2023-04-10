package com.example.comp3350.softwaresavants.presentation_it2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.List;
import com.example.comp3350.softwaresavants.Business.Search;

public class RecipeActivity extends AppCompatActivity {
    private TextView recipeDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeDisplay = findViewById(R.id.recipe_text);

        List<String> selectedVegetables = getIntent().getStringArrayListExtra("selectedVegetables");
        List<String> selectedProteins = getIntent().getStringArrayListExtra("selectedProteins");

        Search RecipeFinder = new Search();
        // Use Search class to find a recipe based on selected ingredients
        String recipe = RecipeFinder.findRecipe(selectedVegetables, selectedProteins);

        // Display the recipe on the screen
        recipeDisplay.setText(recipe);
    }
}

