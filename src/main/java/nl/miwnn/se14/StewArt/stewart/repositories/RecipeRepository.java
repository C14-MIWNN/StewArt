package nl.miwnn.se14.StewArt.stewart.repositories;

import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository <Recipe, Long> {
    Optional<List<Recipe>> findByTitleContaining(String searchTerm);

    Optional<List<Recipe>> findByRecipeAuthor_Username(String username);

    Optional<List<Recipe>> findByRecipeAuthor_UsernameAndTitleContaining(String username, String searchTerm);
}
