package nl.miwnn.se14.StewArt.stewart.repositories;


import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository <Recipe, Long> {
}
