package nl.miwnn.se14.StewArt.stewart.controller;

import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
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

    public InitializeController(StewArtUserService stewArtUserService) {
        this.stewArtUserService = stewArtUserService;
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
    }


    private StewArtUser makeStewArtUser(String username, String password) {
        StewArtUser user = new StewArtUser();
        user.setUsername(username);
        user.setPassword(password);
        stewArtUserService.save(user);
        return user;
    }
}
