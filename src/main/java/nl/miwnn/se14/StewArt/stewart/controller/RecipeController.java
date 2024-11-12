package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ingeborg Frentz
 * Handle requests primarily related to recipes
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    // TO DO add connection to userRepository

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/overview")
    private String showRecipeOverview(Model datamodel) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());

        return "recipeOverview";
    }

    @GetMapping("/new")
    private String showRecipeForm(Model datamodel) {
        datamodel.addAttribute("newRecipe", new Recipe());

        return "recipeForm";
    }

    @PostMapping("/new")
    private String saveOrUpdateRecipe(@ModelAttribute("newRecipe") Recipe recipeToBeSaved, BindingResult result) {
        if(result.hasErrors()) {
            System.err.println(result.getAllErrors());

            return "redirect:/recipe/overview";
        }

        recipeRepository.save(recipeToBeSaved);

        return "redirect:/recipe/overview";
    }

    @GetMapping("/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.deleteById(recipeId);

        return "redirect:/recipe/overview";
    }
}
