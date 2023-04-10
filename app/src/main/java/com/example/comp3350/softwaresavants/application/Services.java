package com.example.comp3350.softwaresavants.application;

import com.example.comp3350.softwaresavants.persistence.IngredientPersistence;
import com.example.comp3350.softwaresavants.persistence.RecipePersistence;
import com.example.comp3350.softwaresavants.persistence.hsqldb.IngredientPersistenceHSQLDB;
import com.example.comp3350.softwaresavants.persistence.hsqldb.RecipePersistenceHSQLDB;


public class Services {
    private static IngredientPersistence ingredientPersistence = null;
    private static RecipePersistence recipePersistence = null;

    public static synchronized IngredientPersistence getIngredientPersistence() {
        if (ingredientPersistence == null) {
            ingredientPersistence = new IngredientPersistenceHSQLDB(MyApp.getDBPathName());
        }

        return ingredientPersistence;
    }

    public static synchronized RecipePersistence getRecipePersistence() {
        if (recipePersistence == null) {
            recipePersistence = new RecipePersistenceHSQLDB(MyApp.getDBPathName());
        }

        return recipePersistence;
    }
}
