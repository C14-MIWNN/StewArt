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

    private double amount;
    private IngredientUnits unit;

    @ManyToOne
    private Ingredient ingredient;

    public RecipeIngredient() {
    }

    public RecipeIngredient(double amount, IngredientUnits unit, Ingredient ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
    }

    public String getIngredientName() {
        return ingredient.getIngredientName();
    }
}
