package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.StewArtUserRepository;
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
    private final StewArtUserRepository stewArtUserRepository;

    public InitializeController(StewArtUserService stewArtUserService, RecipeRepository recipeRepository, StewArtUserRepository stewArtUserRepository) {
        this.stewArtUserService = stewArtUserService;
        this.recipeRepository = recipeRepository;
        this.stewArtUserRepository = stewArtUserRepository;
    }

    @EventListener
    private void onServerStart(ContextRefreshedEvent event) {
        if (stewArtUserService.getAllUsers().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
        makeStewArtUser("Luc", "DevPassword123");
        makeStewArtUser("Ingeborg", "Makkelijk");

        Recipe tortilla = makeRecipe("Flour Tortillas", 20.0, 40.0, "Homemade flour tortillas",
                """
                3 cups all-purpose flour\n
                1 teaspoon kosher salt, I use Morton's\n
                1 teaspoon baking powder\n
                ⅓ cup extra virgin olive oil, vegetable oil or other fairly neutral flavored oil\n
                1 cup warm water""",
                """
                Combine flour, salt and baking powder in a medium-size bowl. Using a sturdy silicone spatuala or a sturdy wooden spoon, mix dry ingredients until well combined.\n" +
                Make a well in the center of the dry ingredients and add the oil and water. Stir well from the bottom up, until all dry ingredients are incorporated and the dough begins to come together and form a shaggy ball.\n" +
                Turn dough out onto a lightly floured work surface and knead for 1-2 minutes until the dough is nice and smooth. Proceed with step number 3 below for the remainder of the recipe.""",
                "https://pixabay.com/photos/tortilla-cooking-food-taco-6602186/");
    }


    private StewArtUser makeStewArtUser(String username, String password) {
        StewArtUser user = new StewArtUser();
        user.setUsername(username);
        user.setPassword(password);
        stewArtUserService.save(user);
        return user;
    }


    private Recipe makeRecipe(String title,
                              Double prepTime,
                              Double cookTime,
                              String shortDescription,
                              String ingredients,
                              String instructions,
                              String image) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setPrepTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setShortDescription(shortDescription);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);
        recipe.setImageUrl(image);

        //TO DO add username to recipe

        recipeRepository.save(recipe);
        return recipe;

    }
}
