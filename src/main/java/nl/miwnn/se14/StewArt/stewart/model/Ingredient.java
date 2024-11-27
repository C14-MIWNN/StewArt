package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author Luc Weerts
 * Ingredient in a recipe
 */
@Entity
public class Ingredient {

    @Id @GeneratedValue
    private Long ingredientId;

    @Column(unique = true)
    private String ingredientName;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
