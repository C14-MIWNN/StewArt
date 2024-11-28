package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.dto.RecipeDTO;
import nl.miwnn.se14.StewArt.stewart.enums.IngredientUnits;
import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeIngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.service.StewArtUserService;
import nl.miwnn.se14.StewArt.stewart.service.mapper.RecipeMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Luc Weerts
 * Set some initial data in the database for testing puposes
 */
@Controller
public class InitializeController {
    private final StewArtUserService stewArtUserService;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public InitializeController(
            StewArtUserService stewArtUserService,
            RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            RecipeIngredientRepository recipeIngredientRepository) {
        this.stewArtUserService = stewArtUserService;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @EventListener
    private void onServerStart(ContextRefreshedEvent event) {
        if (stewArtUserService.getAllUsers().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
        StewArtUser luc = makeStewArtUser("Luc", "DevPassword123", "ADMIN");
        StewArtUser ingeborg = makeStewArtUser("Ingeborg", "Makkelijk", "ADMIN");
        StewArtUser demo = makeStewArtUser("demo", "demow8w", "USER");

        Ingredient allPurposeFlour = makeIngredient("all-purpose flour");
        Ingredient kosherSalt = makeIngredient("kosher salt, I use Morton's");
        Ingredient bakingPowder = makeIngredient("baking powder");
        Ingredient oilBroad = makeIngredient("extra virgin olive oil, vegetable oil or other fairly neutral flavored oil");
        Ingredient warmWater = makeIngredient("warm water");
        Set<RecipeIngredient> tortillaIngredientSet = Set.of(
                new RecipeIngredient(3, IngredientUnits.cup, allPurposeFlour),
                new RecipeIngredient(1, IngredientUnits.tsp, kosherSalt),
                new RecipeIngredient(1, IngredientUnits.tsp, bakingPowder),
                new RecipeIngredient(1/3.0, IngredientUnits.cup, oilBroad),
                new RecipeIngredient(1, IngredientUnits.cup, warmWater)
        );
        ArrayList<RecipeIngredient> tortillaIngredients = new ArrayList<>(tortillaIngredientSet);


        Recipe tortilla = makeRecipe("Flour Tortillas", 20, 40, 4, "Homemade flour tortillas",
                tortillaIngredients,
                """
                Combine flour, salt and baking powder in a medium-size bowl. Using a sturdy silicone spatula or a sturdy wooden spoon, mix dry ingredients until well combined.
                Make a well in the center of the dry ingredients and add the oil and water. Stir well from the bottom up, until all dry ingredients are incorporated and the dough begins to come together and form a shaggy ball.
                Turn dough out onto a lightly floured work surface and knead for 1-2 minutes until the dough is nice and smooth. Proceed with step number 3 below for the remainder of the recipe.""",
                "https://cdn.pixabay.com/photo/2020/05/05/21/48/maiz-5135234_1280.jpg",
                ingeborg
        );

        Ingredient darkChocolate = makeIngredient("dark vegan chocolate");
        Ingredient heavyCream = makeIngredient("vegan heavy cream");
        Ingredient vanillaIceCream = makeIngredient("vegan vanilla ice cream (Hertog)");
        Set<RecipeIngredient> iceCreamIngredientSet = Set.of(
                new RecipeIngredient(200, IngredientUnits.gram, darkChocolate),
                new RecipeIngredient(200, IngredientUnits.mL, heavyCream),
                new RecipeIngredient(0.9, IngredientUnits.liter, vanillaIceCream)
        );


        ArrayList<RecipeIngredient> iceCreamIngredients = new ArrayList<>(iceCreamIngredientSet);

        Recipe iceCream = makeRecipe(
                "Vegan vanilla dessert",
                20,
                10,
                6,
                "Vegan ice cream with chocolate sauce",
                iceCreamIngredients,
                """
                        Chop the chocolate and bring vegan heavy cream to a boil.
                        Take off heat, add the chocolate and stir until all chocolate is molten.
                        Pour over ice cream and serve.""",
                "https://cdn.pixabay.com/photo/2024/06/02/17/02/ice-cream-8804688_1280.jpg",
                luc);

        Ingredient eggs = makeIngredient("eggs");
        Ingredient sugar = makeIngredient("sugar");
        Ingredient vanillaExtract = makeIngredient("vanilla extract");
        Ingredient butter = makeIngredient("butter");
        Ingredient greekYoghurt = makeIngredient("plain Greek yoghurt");
        Ingredient orange = makeIngredient("orange");
        Set<RecipeIngredient> orangeCakeIngredientSet = Set.of(
                new RecipeIngredient(3, IngredientUnits.whole, eggs),
                new RecipeIngredient(1, IngredientUnits.cup, sugar),
                new RecipeIngredient(1.75, IngredientUnits.cup, allPurposeFlour),
                new RecipeIngredient(2.5, IngredientUnits.tsp, bakingPowder),
                new RecipeIngredient(1, IngredientUnits.tsp, vanillaExtract),
                new RecipeIngredient(100, IngredientUnits.gram, butter),
                new RecipeIngredient(0.33333333333, IngredientUnits.cup, greekYoghurt),
                new RecipeIngredient(1, IngredientUnits.whole, orange)
                );
        ArrayList<RecipeIngredient> orangeCakeIngredients = new ArrayList<>(orangeCakeIngredientSet);

        Recipe orangeCake = makeRecipe("Sicilian Orange Cake", 30, 60, 8,
                "Delicious and easy Sicilian Orange Cake", orangeCakeIngredients,
                """
                        Preheat oven to 175°C and prepare an 8" (20cm) spring form pan by spraying with oil (or butter) and line with baking paper.
                        Place the sugar and eggs in a large bowl and beat with a mixer until light and fluffy.
                        Sift the flour with the baking powder then add to the egg mix a little at a time along with the softened butter. Mix until completely blended, then stir in the yoghurt.
                        In a food processor, process the whole orange until it is almost pureed. Add the orange to the cake mix along with the vanilla and stir until evenly combined.  Pour the batter into the prepared tin.
                        Bake for 50-60 minutes (depending on your oven), but test with a skewer to make sure the orange cake is done before removing from the oven. Allow to cool for about 15 minutes, then remove the side of the spring form pan.
                        Make the orange glaze. Melt the sugar in the orange juice and allow to simmer for a few minutes, just until the liquid has a syrupy consistency.
                        Spoon and brush over the top of the cake and allow to cool completely before cutting.""",
                "https://cdn.pixabay.com/photo/2020/10/27/09/49/cake-5690186_1280.jpg", ingeborg);


        Ingredient lentils = makeIngredient("lentils");
        Ingredient onions = makeIngredient("onion");
        Ingredient carrrots = makeIngredient("carrot");
    }

    private StewArtUser makeStewArtUser(String username, String password, String role) {
        StewArtUser user = new StewArtUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        stewArtUserService.save(user);
        return user;
    }

    private Ingredient makeIngredient(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(name);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    private void saveRecipeIngredients(Set<RecipeIngredient> ingredients) {
        recipeIngredientRepository.saveAll(ingredients);
    }


    private Recipe makeRecipe(String title,
                              int prepTime,
                              int cookTime,
                              int servings,
                              String shortDescription,
                              ArrayList<RecipeIngredient> ingredients,
                              String instructions,
                              String image,
                              StewArtUser user) {
        RecipeDTO recipeDto = new RecipeDTO();
        recipeDto.setTitle(title);
        recipeDto.setPrepTime(prepTime);
        recipeDto.setCookTime(cookTime);
        recipeDto.setServings(servings);
        recipeDto.setShortDescription(shortDescription);
        recipeDto.setAllIngredients(ingredients);
        recipeDto.setInstructions(instructions);
        recipeDto.setImageUrl(image);

        Recipe recipe = RecipeMapper.fromDTO(recipeDto);
        saveRecipeIngredients(recipe.getIngredients());


        recipe.setRecipeAuthor(user);
        recipeRepository.save(recipe);
        return recipe;

    }
}
