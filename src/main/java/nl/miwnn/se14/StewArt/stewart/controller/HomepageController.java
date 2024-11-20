package nl.miwnn.se14.StewArt.stewart.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.StewArt.stewart.dto.StewArtUserDTO;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.StewArtUserRepository;
import nl.miwnn.se14.StewArt.stewart.service.StewArtUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private final RecipeRepository recipeRepository;
    private final StewArtUserRepository stewArtUserRepository;
    private final StewArtUserService stewArtUserService;

    public HomepageController(RecipeRepository recipeRepository, StewArtUserRepository stewArtUserRepository, StewArtUserService stewArtUserService) {
        this.recipeRepository = recipeRepository;
        this.stewArtUserRepository = stewArtUserRepository;
        this.stewArtUserService = stewArtUserService;
    }

    @GetMapping("/")
    private String showHomepage(Model datamodel) {
        datamodel.addAttribute("allUsers", stewArtUserService.getAllUsers());
        datamodel.addAttribute("formUser", new StewArtUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        datamodel.addAttribute("searchForm", new Recipe());

        return "homepage";
    }

    @PostMapping("/search")
    private String showRecipesByTitleSearch(@ModelAttribute("searchForm") Recipe recipe, BindingResult result, Model datamodel) {

        Optional<List<Recipe>> searchResultList = recipeRepository.findByTitleContaining(recipe.getTitle());

        if (searchResultList.isEmpty() || searchResultList.get().isEmpty()) {
            result.rejectValue("title", "search.results.empty", "No recipes found with this search term");
        }

        if (result.hasErrors()) {
            return "homepage";
        }

        datamodel.addAttribute("allRecipes", searchResultList.get());
        return "recipeOverview";
    }
}
