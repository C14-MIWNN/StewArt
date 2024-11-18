package nl.miwnn.se14.StewArt.stewart.repositories;


import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<List<Ingredient>> findByIngredientNameContaining(String searchTerm);

}
