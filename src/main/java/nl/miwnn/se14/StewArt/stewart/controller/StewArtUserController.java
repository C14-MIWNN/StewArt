package nl.miwnn.se14.StewArt.stewart.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.StewArt.stewart.dto.StewArtUserDTO;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.service.StewArtUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luc Weerts
 * Handle all requests related primarily to StewArtUsers
 */
@Controller
@RequestMapping("/user")
public class StewArtUserController {
    private final StewArtUserService stewArtUserService;

    public StewArtUserController(StewArtUserService stewArtUserService) {
        this.stewArtUserService = stewArtUserService;
    }

    @GetMapping("/overview")
    private String showUserOverview(Model datamodel) {
        datamodel.addAttribute("allUsers", stewArtUserService.getAllUsers());
        datamodel.addAttribute("formUser", new StewArtUserDTO());
        datamodel.addAttribute("formModalHidden", true);

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser") @Valid StewArtUserDTO userDtoToBeSaved,
                                    BindingResult result, Model datamodel) {
        if (stewArtUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is not available");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPasswordConfirm())) {
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", stewArtUserService.getAllUsers());
            datamodel.addAttribute("formUser", userDtoToBeSaved);
            datamodel.addAttribute("formModalHidden", false);
            datamodel.addAttribute("searchForm", new Recipe());
            return "homepage";
        }

        userDtoToBeSaved.setRole("USER");

        stewArtUserService.save(userDtoToBeSaved);
        return "redirect:/";
    }
}
