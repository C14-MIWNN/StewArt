package nl.miwnn.se14.StewArt.stewart.service.mapper;

import nl.miwnn.se14.StewArt.stewart.dto.RecipeDTO;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;

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

    public static RecipeDTO fromRecipeAddAllIngredients(Recipe recipe, IngredientRepository ingredientRepository) {
        RecipeDTO recipeDTO = new RecipeDTO(true, ingredientRepository);

        recipeDTO.setTitle(recipe.getTitle());
        recipeDTO.setShortDescription(recipe.getShortDescription());
        recipeDTO.setPrepTime(recipe.getPrepTime());
        recipeDTO.setCookTime(recipe.getCookTime());
        recipeDTO.setInstructions(recipe.getInstructions());
        recipeDTO.setImageUrl(recipe.getImageUrl());

        addRecipeIngredients(recipe, recipeDTO);

        return recipeDTO;
    }

    private static void addRecipeIngredients(Recipe recipe, RecipeDTO recipeDTO) {
        ArrayList<String> presentIngredients = new ArrayList<>();
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            presentIngredients.add(ingredient.getIngredientName());
        }
        recipeDTO.getAllIngredients().removeIf((n) -> presentIngredients.contains(n.getIngredientName()));
        recipeDTO.getAllIngredients().addAll(recipe.getIngredients());
    }
}
