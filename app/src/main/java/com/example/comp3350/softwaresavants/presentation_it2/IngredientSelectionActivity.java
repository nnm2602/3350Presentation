package com.example.comp3350.softwaresavants.presentation_it2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp3350.softwaresavants.Objects.Ingredient;
import com.example.comp3350.softwaresavants.application.Services;
import com.example.comp3350.softwaresavants.persistence.IngredientPersistence;
import com.example.comp3350.softwaresavants.persistence.RecipePersistence;

import java.util.ArrayList;
import java.util.List;

public class IngredientSelectionActivity extends AppCompatActivity {

    private ArrayList<String> selectedVegetables = new ArrayList<>();
    private ArrayList<String> selectedProteins = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_selection);

        // Get the layout containers for vegetables and proteins
        LinearLayout vegetableLayout = findViewById(R.id.vegetableLayout);
        LinearLayout proteinLayout = findViewById(R.id.proteinLayout);

        //get the database
        IngredientPersistence ingredientDatabase = Services.getIngredientPersistence();

        // Create the vegetable checkboxes dynamically
        List<Ingredient> vegetablesObj = ingredientDatabase.getAllVegetables();
        List<String> vegetables = new ArrayList<String>();
        for (Ingredient veggie : vegetablesObj) {
            vegetables.add(veggie.getIngredientName());
        }

        for (String vegetable : vegetables) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(vegetable);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedVegetables.add(vegetable);
                    } else {
                        selectedVegetables.remove(vegetable);
                    }
                }
            });
            vegetableLayout.addView(checkBox);
        }

        // Create the protein checkboxes dynamically
        List<Ingredient> proteinsObj = ingredientDatabase.getAllVegetables();
        List<String> proteins = new ArrayList<String>();
        for (Ingredient pro : proteinsObj) {
            vegetables.add(pro.getIngredientName());
        }
        for (String protein : proteins) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(protein);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedProteins.add(protein);
                    } else {
                        selectedProteins.remove(protein);
                    }
                }
            });
            proteinLayout.addView(checkBox);
        }

        // Handle button click to generate recipe
        Button generateRecipeBtn = findViewById(R.id.generateRecipeBtn);
        generateRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientSelectionActivity.this, RecipeActivity.class);
                intent.putExtra("selectedVegetables", selectedVegetables);
                intent.putExtra("selectedProteins", selectedProteins);
                startActivity(intent);
            }
        });
    }
}

