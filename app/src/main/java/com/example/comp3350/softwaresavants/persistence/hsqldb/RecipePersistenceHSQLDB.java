package com.example.comp3350.softwaresavants.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.example.comp3350.softwaresavants.Objects.Recipe;
import com.example.comp3350.softwaresavants.persistence.RecipePersistence;

public class RecipePersistenceHSQLDB implements RecipePersistence {

    private final String dbPath;

    public RecipePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Recipe fromResultSet(final ResultSet rs) throws SQLException {
        final String recipeName = rs.getString("RECIPENAME");
        final String tutorial = rs.getString("TUTORIAL");
        final int vegCount = rs.getInt("VEGCOUNT");
        final int proteinCount = rs.getInt("PROTEINCOUNT");

        return new Recipe(recipeName, tutorial, vegCount, proteinCount);
    }

    @Override
    public Recipe getRecipeFrame(int numVegetables, int numProteins) {
        Recipe recipe = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "SELECT * FROM recipes WHERE vegCount <= ? AND proteinCount <= ? ORDER BY vegCount DESC, proteinCount DESC LIMIT 1");
            st.setInt(1, numVegetables);
            st.setInt(2, numProteins);

            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                recipe = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Recipe getRecipeByName(String recipeName) {
        Recipe recipe = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM recipes WHERE recipeName = ?");
            st.setString(1, recipeName);

            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                recipe = fromResultSet(rs);
            }
            rs.close();
            st.close();

            return recipe;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void addRecipe(Recipe recipe) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO recipes VALUES (?, ?, ?, ?)");
            st.setString(1, recipe.getRecipeName());
            st.setString(2, recipe.getTutorial());
            st.setInt(3, recipe.getVegCount());
            st.setInt(4, recipe.getProteinCount());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void removeRecipe(String recipeName) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM recipes WHERE recipeName = ?");
            st.setString(1, recipeName);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM recipes");
            final ResultSet rs = st.executeQuery();

            while (rs.next()) {
                recipeList.add(fromResultSet(rs));
            }
            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return recipeList;
    }

}
