package com.example.comp3350.softwaresavants.presentation_it2.Business;

import com.example.comp3350.softwaresavants.persistence.RecipePersistence;
import com.example.comp3350.softwaresavants.Business.Search;
import com.example.comp3350.softwaresavants.Objects.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SearchTest {

    private Search search;

    @Mock
    private RecipePersistence mockRecipePersistence;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        search = new Search(mockRecipePersistence);
    }

    @Test
    public void testFindRecipeWithEnoughIngredients() {
        // Arrange
        List<String> vegetables = Arrays.asList("carrots", "broccoli");
        List<String> proteins = Arrays.asList("chicken");
        String recipeFrame = "Cook [vegetable1] and [vegetable2] with [protein1].";
        List<String> expectedIngredients = Arrays.asList("carrots", "broccoli", "chicken");

        when(mockRecipePersistence.getRecipeFrame(2, 1)).thenReturn(new Recipe("Recipe 1", recipeFrame, 2, 1));

        // Act
        String actualRecipe = search.findRecipe(vegetables, proteins);

        // Assert
        verify(mockRecipePersistence, times(1)).getRecipeFrame(2, 1);
        assertEquals("Cook carrots and broccoli with chicken.", actualRecipe);
    }

    @Test
    public void testFindRecipeWithNotEnoughIngredients() {
        // Arrange
        List<String> vegetables = Arrays.asList("carrots");
        List<String> proteins = new ArrayList<>();
        String recipeFrame = "Cook [vegetable1] and [vegetable2] with [protein1].";

        when(mockRecipePersistence.getRecipeFrame(1, 0)).thenReturn(new Recipe("Recipe 2", recipeFrame, 1, 0));

        // Act
        String actualRecipe = search.findRecipe(vegetables, proteins);

        // Assert
        verify(mockRecipePersistence, times(1)).getRecipeFrame(1, 0);
        assertEquals("Not enough ingredients to generate recipe.", actualRecipe);
    }
    @Test
    public void testFindRecipeWithExactIngredients() {
        // Arrange
        List<String> vegetables = Arrays.asList("carrots", "broccoli", "peas");
        List<String> proteins = Arrays.asList("chicken", "beef");
        String recipeFrame = "Cook [vegetable1], [vegetable2], and [vegetable3] with [protein1] and [protein2].";

        when(mockRecipePersistence.getRecipeFrame(3, 2)).thenReturn(new Recipe("Recipe 3", recipeFrame, 3, 2));

        // Act
        Search search = new Search(mockRecipePersistence);
        String actualRecipe = search.findRecipe(vegetables, proteins);

        // Assert
        verify(mockRecipePersistence, times(1)).getRecipeFrame(3, 2);
        assertEquals("Cook carrots, broccoli, and peas with chicken and beef.", actualRecipe);
    }

    @Test
    public void testFindRecipeWithMoreIngredientsThanNeeded() {
        // Arrange
        List<String> vegetables = Arrays.asList("carrots", "broccoli", "peas", "spinach", "onions", "mushrooms");
        List<String> proteins = Arrays.asList("chicken");
        String expectedMessage = "Cook carrots, broccoli, and peas with chicken.";

        when(mockRecipePersistence.getRecipeFrame(6, 1)).thenReturn(new Recipe("Recipe 4", "Cook [vegetable1], [vegetable2], and [vegetable3] with [protein1].",6 ,1));

        // Act
        Search search = new Search(mockRecipePersistence);
        String actualRecipe = search.findRecipe(vegetables, proteins);

        // Assert
        verify(mockRecipePersistence, times(1)).getRecipeFrame(6, 1);
        assertEquals(expectedMessage, actualRecipe);
    }

    @Test
    public void testFindRecipeWithNoIngredients() {
        // Arrange
        List<String> vegetables = new ArrayList<>();
        List<String> proteins = new ArrayList<>();
        String recipeFrame = "Cook [vegetable1] and [vegetable2] with [protein1].";

        when(mockRecipePersistence.getRecipeFrame(0, 0)).thenReturn(new Recipe("Recipe 5", recipeFrame, 0, 0));

        // Act
        Search search = new Search(mockRecipePersistence);
        String actualRecipe = search.findRecipe(vegetables, proteins);

        // Assert
        verify(mockRecipePersistence, times(1)).getRecipeFrame(0, 0);
        assertEquals("Not enough ingredients to generate recipe.", actualRecipe);
    }
}
