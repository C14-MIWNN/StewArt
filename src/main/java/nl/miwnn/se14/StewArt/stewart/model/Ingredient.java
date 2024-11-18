package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

/**
 * @author Luc Weerts
 * Ingredient in a recipe
 */
@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long IngredientId;

    private String ingredientName;

    // todo ingredient can have tags that automatically show up as recipe tags, like 'nut' for nut allergy.


    public Long getIngredientId() {
        return IngredientId;
    }

    public void setIngredientId(Long ingredientId) {
        IngredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
