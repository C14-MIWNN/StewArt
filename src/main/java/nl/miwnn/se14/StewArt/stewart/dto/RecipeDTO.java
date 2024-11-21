package nl.miwnn.se14.StewArt.stewart.dto;

import jakarta.validation.constraints.NotBlank;
import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;

import java.util.*;

/**
 * @author Luc Weerts
 * Capture the information needed for the create-a-new-recipe form
 */
public class RecipeDTO {
    @NotBlank
    private String title;
    private String shortDescription;
    private int prepTime;
    private int cookTime;
    private String instructions;
    private String imageUrl;

    private ArrayList<RecipeIngredient> allIngredients;

    public RecipeDTO(boolean fillAllIngredients, IngredientRepository ingredientRepository) {
        if (fillAllIngredients) {
            allIngredients = new ArrayList<>();
            for (Ingredient ingredient : ingredientRepository.findAll()) {
                String name = ingredient.getIngredientName();
                allIngredients.add(new RecipeIngredient(ingredient));
            }
        }
    }

    public RecipeDTO() {
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<RecipeIngredient> getAllIngredients() {

        return allIngredients;
    }

    public void setAllIngredients(ArrayList<RecipeIngredient> allIngredients) {
        this.allIngredients = allIngredients;
    }
}
