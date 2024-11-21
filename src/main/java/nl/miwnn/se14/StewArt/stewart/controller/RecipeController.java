package nl.miwnn.se14.StewArt.stewart.controller;


import jakarta.validation.Valid;
import nl.miwnn.se14.StewArt.stewart.dto.RecipeDTO;
import nl.miwnn.se14.StewArt.stewart.enums.IngredientUnits;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeIngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.StewArtUserRepository;
import nl.miwnn.se14.StewArt.stewart.service.StewArtUserService;
import nl.miwnn.se14.StewArt.stewart.service.mapper.RecipeMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

/**
 * @author Ingeborg Frentz
 * Handle requests primarily related to recipes
 */
@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final StewArtUserRepository stewArtUserRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, StewArtUserRepository stewArtUserRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.stewArtUserRepository = stewArtUserRepository;
        this.ingredientRepository = ingredientRepository;
    }

    private void setupRecipeOverview(Model datamodel, List<Recipe> recipeList, boolean newFormRecipe) {
        datamodel.addAttribute("allRecipes", recipeList);
        datamodel.addAttribute("searchForm", new Recipe());
        if (newFormRecipe) {
            datamodel.addAttribute("formRecipe", new RecipeDTO(true, ingredientRepository));
        }
        datamodel.addAttribute("allStewArtUsers", stewArtUserRepository.findAll());
    }

    private String setupRecipeModalOverview(Model datamodel, Recipe formRecipe, boolean formModalHidden) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        datamodel.addAttribute("formRecipe", formRecipe);
        datamodel.addAttribute("allStewArtUsers", stewArtUserRepository.findAll());
        datamodel.addAttribute("formModalHidden", formModalHidden);

        return "recipeOverview";
    }

    @GetMapping("/recipe/overview")
    private String showRecipeOverview(Model datamodel) {

        setupRecipeOverview(datamodel, recipeRepository.findAll(), true);
        return "recipeOverview";
    }

    @PostMapping("/recipe/search")
    private String showRecipesByTitleSearch(
            @ModelAttribute("searchForm") Recipe recipe, BindingResult result, Model datamodel) {

        Optional<List<Recipe>> searchResultList = recipeRepository.findByTitleContaining(recipe.getTitle());

        if (searchResultList.isEmpty() || searchResultList.get().isEmpty()) {
            result.rejectValue("title", "search.results.empty",
                    "No recipes found with your search term");
        }

        if (result.hasErrors()) {
            return "recipeOverview";
        }

        datamodel.addAttribute("allRecipes", searchResultList.get());
        return "recipeOverview";
    }

    @GetMapping("/recipe/my_recipes")
    private String showMyRecipes(Model datamodel) {
        List<Recipe> myRecipes = getMyRecipes();

        datamodel.addAttribute("allUnits", IngredientUnits.values());
        setupRecipeOverview(datamodel, myRecipes, true);
        datamodel.addAttribute("formModalHidden", true);
        return "myRecipes";
    }

    private List<Recipe> getMyRecipes() {
        String currentUsername = StewArtUserService.getCurrentUsername();

        Optional<List<Recipe>> myRecipesOptional = recipeRepository.findByRecipeAuthor_Username(currentUsername);
        List<Recipe> myRecipes = myRecipesOptional.orElseThrow(
                () -> new IllegalArgumentException(String.format(
                        "Recipe search for user %s returned no list", currentUsername))
        );
        return myRecipes;
    }

    @PostMapping("/recipe/my_recipes_search")
    private String showMyRecipesByTitleSearch(
            @ModelAttribute("searchForm") Recipe recipe, BindingResult result, Model datamodel) {
        String currentUsername = StewArtUserService.getCurrentUsername();

        Optional<List<Recipe>> myRecipesOptional = recipeRepository.findByRecipeAuthor_UsernameAndTitleContaining(
                        currentUsername, recipe.getTitle());

        if (myRecipesOptional.isEmpty() || myRecipesOptional.get().isEmpty()) {
            result.rejectValue("title", "search.results.empty",
                    "No recipes found with your search term");
        }

        if (result.hasErrors()) {
            return "myRecipes";
        }

        setupRecipeOverview(datamodel, myRecipesOptional.get(), true);
        return "myRecipes";
    }


    @GetMapping("/recipe/add_recipe")
    private String showRecipeModal(Model datamodel) {
        datamodel.addAttribute("formModalHidden", false);

        return "redirect:/recipe/my_recipes";
    }

    @PostMapping("/recipe/save")
    private String saveOrUpdateRecipe(@ModelAttribute("formRecipe") @Valid RecipeDTO recipeDtoToBeSaved, BindingResult result,
                                      Model datamodel) {

        // todo show errors to user in the modal

        if (result.hasErrors()) {
            List<Recipe> myRecipes = getMyRecipes();
            datamodel.addAttribute("allUnits", IngredientUnits.values());
            setupRecipeOverview(datamodel, myRecipes, false);
            datamodel.addAttribute("formModalHidden", false);
            return "myRecipes";
        }

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<StewArtUser> userOptional = stewArtUserRepository.findByUsername(currentUsername);
        StewArtUser user = userOptional.orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("Username was not found in the database", currentUsername))
        );

        Recipe recipeToBeSaved = RecipeMapper.fromDTO(recipeDtoToBeSaved);
        recipeIngredientRepository.saveAll(recipeToBeSaved.getIngredients());

        recipeToBeSaved.setRecipeAuthor(user);
        recipeRepository.save(recipeToBeSaved);

        return "redirect:/recipe/my_recipes";
    }

    @GetMapping("/recipe/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);

        return "redirect:/recipe/overview";
    }

    private String setupRecipeDetail(Model datamodel, Recipe recipeToShow, Recipe formRecipe, boolean formModalHidden) {
        datamodel.addAttribute("recipe", recipeToShow);
        datamodel.addAttribute("formRecipe", formRecipe);
        //TODO add username of author
        datamodel.addAttribute("formModalHidden", formModalHidden);

        return "recipeDetails";
    }

    @GetMapping("/recipe/detail/{recipeId}")
    private String showRecipeDetailPage(@PathVariable("recipeId") Long recipeId, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/overview";
        }

        return setupRecipeDetail(datamodel, recipeOptional.get(), recipeOptional.get(), true);
    }
}
