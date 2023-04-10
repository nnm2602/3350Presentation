package com.example.comp3350.softwaresavants.persistence.hsqldb;
import com.example.comp3350.softwaresavants.Objects.Ingredient;
import com.example.comp3350.softwaresavants.persistence.IngredientPersistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredientPersistenceHSQLDB implements IngredientPersistence {

    private final String dbPath;

    public IngredientPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Ingredient fromResultSet(final ResultSet rs) throws SQLException {
        final String ingredientName = rs.getString("ingredientName");
        final String type = rs.getString("type");
        return new Ingredient(ingredientName, type);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        final List<Ingredient> ingredients = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM ingredients");
            while (rs.next()) {
                final Ingredient ingredient = fromResultSet(rs);
                ingredients.add(ingredient);
            }
            rs.close();
            st.close();

            return ingredients;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Ingredient> getAllVegetables() {
        final List<Ingredient> vegetables = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ingredients WHERE type=?");
            st.setString(1, "vegetable");

            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Ingredient vegetable = fromResultSet(rs);
                vegetables.add(vegetable);
            }
            rs.close();
            st.close();

            return vegetables;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<Ingredient> getAllProteins() {
        final List<Ingredient> proteins = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ingredients WHERE type=?");
            st.setString(1, "protein");

            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Ingredient protein = fromResultSet(rs);
                proteins.add(protein);
            }
            rs.close();
            st.close();

            return proteins;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Ingredient getIngredientByName(String name) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM ingredients WHERE ingredientName=?");
            st.setString(1, name);

            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                final Ingredient ingredient = fromResultSet(rs);
                rs.close();
                st.close();
                return ingredient;
            } else {
                rs.close();
                st.close();
                return null;
            }
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void addIngredient(Ingredient ingredient) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO ingredients VALUES(?, ?)");
            st.setString(1, ingredient.getIngredientName());
            st.setString(2, ingredient.getType());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void removeIngredient(String name) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM ingredients WHERE name = ?");
            st.setString(1, name);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

}