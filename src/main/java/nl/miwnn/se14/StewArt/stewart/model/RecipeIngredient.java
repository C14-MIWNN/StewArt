package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.*;
import nl.miwnn.se14.StewArt.stewart.enums.IngredientUnits;

/**
 * @author Luc Weerts
 * Couples a type of ingredient to a specific recipe with corresponding amount and unit
 */
@Entity
public class RecipeIngredient {

    @Id @GeneratedValue
    private Long recipeIngredientId;

    private Double amount;
    private IngredientUnits unit;

    @ManyToOne
    private Ingredient ingredient;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public RecipeIngredient(double amount, IngredientUnits unit, Ingredient ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
    }

    public RecipeIngredient(double amount, IngredientUnits unit, Ingredient ingredient, Long recipeIngredientId) {
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
        this.recipeIngredientId = recipeIngredientId;
    }

    public String getIngredientName() {
        return ingredient.getIngredientName();
    }

    public void setIngredientName(String name) {
        ingredient.setIngredientName(name);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Long getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(Long recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public IngredientUnits getUnit() {
        return unit;
    }

    public void setUnit(IngredientUnits unit) {
        this.unit = unit;
    }

}
