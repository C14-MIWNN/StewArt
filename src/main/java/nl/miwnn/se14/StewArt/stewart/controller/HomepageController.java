package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.dto.StewArtUserDTO;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author Ingeborg Frentz
 * Handle all requests related primarily to Homepage
 */
@Controller
public class HomepageController {

    protected final RecipeRepository recipeRepository;

    public HomepageController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public String setupHomepage(Model datamodel) {
        Model anotherModel = new BindingAwareModelMap();
        datamodel.addAttribute("formUser", new StewArtUserDTO());
        if (!datamodel.containsAttribute("formModalHidden")) {
            datamodel.addAttribute("formModalHidden", true);
        }
        if (!datamodel.containsAttribute("searchForm")) {
            datamodel.addAttribute("searchForm", new Recipe());
        }
        return "homepage";
    }

    @GetMapping("/")
    private String showHomepage(Model datamodel) {
        return setupHomepage(datamodel);
    }

    @GetMapping("/register")
    private String showRegisterModal(Model datamodel) {
        datamodel.addAttribute("formModalHidden", false);
        return setupHomepage(datamodel);
    }


    @PostMapping("/search")
    private String showRecipesByTitleSearch(@ModelAttribute("searchForm") Recipe recipe, BindingResult result, Model datamodel) {

        Optional<List<Recipe>> searchResultList = recipeRepository.findByTitleContaining(recipe.getTitle());

        if (searchResultList.isEmpty() || searchResultList.get().isEmpty()) {
            result.rejectValue("title", "search.results.empty", "No recipes found with this search term");
        }

        if (result.hasErrors()) {
            return setupHomepage(datamodel);
        }

        datamodel.addAttribute("allRecipes", searchResultList.get());
        return "recipeOverview";
    }
}
