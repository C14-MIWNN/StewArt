package nl.miwnn.se14.StewArt.stewart.service.mapper;

import nl.miwnn.se14.StewArt.stewart.dto.RecipeDTO;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;

import java.util.*;

/**
 * @author Luc Weerts
 * Converts between Recipe Model classes and DTOs
 */
public class RecipeMapper {

    public static Recipe fromDTO(RecipeDTO dto) {
        Recipe recipe = new Recipe();

        recipe.setTitle(dto.getTitle());
        recipe.setShortDescription(dto.getShortDescription());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setInstructions(dto.getInstructions());
        recipe.setImageUrl(dto.getImageUrl());
        recipe.setIngredients(extractRecipeIngredients(dto.getAllIngredients()));
        return recipe;
    }

    private static Set<RecipeIngredient> extractRecipeIngredients(ArrayList<RecipeIngredient> allIngredients) {
        Set<RecipeIngredient> extract = new HashSet<>();
        // Drop ingredients with no amount
        for (RecipeIngredient ingredient : allIngredients) {
            if (ingredient.getAmount() != null) {
                extract.add(ingredient);
            }
        }
        return extract;
    }
}
