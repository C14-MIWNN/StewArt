package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;
import jakarta.validation.constraints.NotEmpty;
import org.apache.catalina.User;

import java.util.Set;

/**
 * @author Ingeborg Frentz
 * Recipe to be viewed on StewArt webpage
 */
@Entity
public class Recipe {

    @Id @GeneratedValue
    private Long recipeId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;
    private Double prepTime;
    private Double cookTime;
    private String ingredients;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    private String imageUrl;

    @ManyToMany
    private Set<StewArtUser> stewArtUsers;


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Double prepTime) {
        this.prepTime = prepTime;
    }

    public Double getCookTime() {
        return cookTime;
    }

    public void setCookTime(Double cookTime) {
        this.cookTime = cookTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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

    public Set<StewArtUser> getStewArtUsers() {
        return stewArtUsers;
    }

    public void setStewArtUsers(Set<StewArtUser> stewArtUsers) {
        this.stewArtUsers = stewArtUsers;
    }
}
