package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.service.StewArtUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Luc Weerts
 * Set some initial data in the database for testing puposes
 */
@Controller
public class InitializeController {
    private final StewArtUserService stewArtUserService;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public InitializeController(StewArtUserService stewArtUserService, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.stewArtUserService = stewArtUserService;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @EventListener
    private void onServerStart(ContextRefreshedEvent event) {
        if (stewArtUserService.getAllUsers().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
        StewArtUser luc = makeStewArtUser("Luc", "DevPassword123");
        StewArtUser ingeborg = makeStewArtUser("Ingeborg", "Makkelijk");

        Ingredient allPurposeFlour = makeIngredient("all-purpose flour");
        Ingredient kosherSalt = makeIngredient("kosher salt, I use Morton's");
        Ingredient bakingPowder = makeIngredient("baking powder");
        Ingredient oilBroad = makeIngredient("extra virgin olive oil, vegetable oil or other fairly neutral flavored oil");
        Ingredient warmWater = makeIngredient("warm water");

        Recipe tortilla = makeRecipe("Flour Tortillas", 20.0, 40.0, "Homemade flour tortillas",
                """
                3 cups all-purpose flour
                1 teaspoon kosher salt, I use Morton's
                1 teaspoon baking powder
                ⅓ cup extra virgin olive oil, vegetable oil or other fairly neutral flavored oil
                1 cup warm water""",
                """
                Combine flour, salt and baking powder in a medium-size bowl. Using a sturdy silicone spatula or a sturdy wooden spoon, mix dry ingredients until well combined.
                " +
                Make a well in the center of the dry ingredients and add the oil and water. Stir well from the bottom up, until all dry ingredients are incorporated and the dough begins to come together and form a shaggy ball.
                " +
                Turn dough out onto a lightly floured work surface and knead for 1-2 minutes until the dough is nice and smooth. Proceed with step number 3 below for the remainder of the recipe.""",
                "https://cdn.pixabay.com/photo/2021/09/06/18/05/tortilla-6602186_960_720.jpg",
                ingeborg
        );

        Recipe iceCream = makeRecipe(
                "Vegan vanilla dessert",
                20.,
                10.,
                "Vegan ice cream with chocolate saus",
                """
                        200 g dark vegan chocolate
                        200 ml vegan heavy cream
                        1 tub vegan vanilla ice cream (Hertog)""",
                """
                        Chop the chocolate and bring vegan heavy cream to a boil.
                        Take off heat, add the chocolate and stir until all chocolate is molten.
                        Pour over ice cream and serve.""",
                "https://cdn.pixabay.com/photo/2024/06/02/17/02/ice-cream-8804688_1280.jpg",
                luc);
    }


    private StewArtUser makeStewArtUser(String username, String password) {
        StewArtUser user = new StewArtUser();
        user.setUsername(username);
        user.setPassword(password);
        stewArtUserService.save(user);
        return user;
    }

    private Ingredient makeIngredient(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(name);
        ingredientRepository.save(ingredient);
        return ingredient;
    }


    private Recipe makeRecipe(String title,
                              Double prepTime,
                              Double cookTime,
                              String shortDescription,
                              String ingredients,
                              String instructions,
                              String image,
                              StewArtUser user) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setPrepTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setShortDescription(shortDescription);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setImageUrl(image);
        recipe.setRecipeAuthor(user);

        recipeRepository.save(recipe);
        return recipe;

    }
}
