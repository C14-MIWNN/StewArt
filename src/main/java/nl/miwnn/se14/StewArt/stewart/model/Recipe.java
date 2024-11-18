package nl.miwnn.se14.StewArt.stewart.model;

import jakarta.persistence.*;

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

    @Column(columnDefinition = "TEXT")
    private String instructions;

    private String imageUrl;

    @ManyToMany
    private Set<RecipeIngredient> ingredients;

    @ManyToMany
    private Set<StewArtUser> likedByUserSet;

    @ManyToOne
    private StewArtUser recipeAuthor;

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

    public Set<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredient> ingredients) {
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

    public Set<StewArtUser> getLikedByUserSet() {
        return likedByUserSet;
    }

    public void setLikedByUserSet(Set<StewArtUser> likedByUserSet) {
        this.likedByUserSet = likedByUserSet;
    }

    public StewArtUser getRecipeAuthor() {
        return recipeAuthor;
    }

    public void setRecipeAuthor(StewArtUser recipeAuthor) {
        this.recipeAuthor = recipeAuthor;
    }
}
