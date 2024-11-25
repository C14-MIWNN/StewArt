package nl.miwnn.se14.StewArt.stewart.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luc Weerts
 * Handles requests primarily related to ingredients
 */
@RequestMapping("/ingredient")
@Controller
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/overview")
    private String showIngredientOverview(Model datamodel) {
        datamodel.addAttribute("allIngredients", ingredientRepository.findAll());
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);

        return "ingredientOverview";
    }

    @PostMapping("/save")
    private String saveIngredient(@ModelAttribute("formIngredient") @Valid Ingredient ingredientToBeSaved,
                                  BindingResult result) {

        if (result.hasErrors()) {
            return "ingredientOverview";
        }

        ingredientRepository.save(ingredientToBeSaved);
        return "redirect:/ingredient/overview";
    }
}
