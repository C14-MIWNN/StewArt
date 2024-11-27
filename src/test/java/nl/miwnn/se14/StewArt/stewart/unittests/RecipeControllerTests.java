package nl.miwnn.se14.StewArt.stewart.unittests;

import nl.miwnn.se14.StewArt.stewart.controller.RecipeController;
import nl.miwnn.se14.StewArt.stewart.dto.RecipeDTO;
import nl.miwnn.se14.StewArt.stewart.enums.IngredientUnits;
import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.IngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeIngredientRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import nl.miwnn.se14.StewArt.stewart.repositories.StewArtUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Ingeborg Frentz
 * Tests for RecipeController
 */
public class RecipeControllerTests {
    private RecipeRepository recipeRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private StewArtUserRepository stewArtUserRepository;
    private IngredientRepository ingredientRepository;
    private RecipeController recipeController;
    private Model datamodel;

    @BeforeEach()
    void beforeEach(){
        recipeRepository = new RecipeRepository() {
            @Override
            public Optional<List<Recipe>> findByTitleContaining(String searchTerm) {
                return Optional.empty();
            }

            @Override
            public Optional<List<Recipe>> findByRecipeAuthor_Username(String username) {
                return Optional.empty();
            }

            @Override
            public Optional<List<Recipe>> findByRecipeAuthor_UsernameAndTitleContaining(String username, String searchTerm) {
                return Optional.empty();
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Recipe> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Recipe> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<Recipe> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Recipe getOne(Long aLong) {
                return null;
            }

            @Override
            public Recipe getById(Long aLong) {
                return null;
            }

            @Override
            public Recipe getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Recipe> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Recipe> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<Recipe> findAll() {
                return List.of();
            }

            @Override
            public List<Recipe> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public <S extends Recipe> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Recipe> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Recipe entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Recipe> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<Recipe> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Recipe> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Recipe> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Recipe> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Recipe> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Recipe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        recipeIngredientRepository = new RecipeIngredientRepository() {
            @Override
            public void flush() {

            }

            @Override
            public <S extends RecipeIngredient> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends RecipeIngredient> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<RecipeIngredient> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public RecipeIngredient getOne(Long aLong) {
                return null;
            }

            @Override
            public RecipeIngredient getById(Long aLong) {
                return null;
            }

            @Override
            public RecipeIngredient getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends RecipeIngredient> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends RecipeIngredient> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends RecipeIngredient> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<RecipeIngredient> findAll() {
                return List.of();
            }

            @Override
            public List<RecipeIngredient> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public <S extends RecipeIngredient> S save(S entity) {
                return null;
            }

            @Override
            public Optional<RecipeIngredient> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(RecipeIngredient entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends RecipeIngredient> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<RecipeIngredient> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<RecipeIngredient> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends RecipeIngredient> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends RecipeIngredient> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends RecipeIngredient> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends RecipeIngredient> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends RecipeIngredient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        stewArtUserRepository = new StewArtUserRepository() {
            @Override
            public Optional<StewArtUser> findByUsername(String username) {
                return Optional.empty();
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends StewArtUser> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends StewArtUser> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<StewArtUser> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public StewArtUser getOne(Long aLong) {
                return null;
            }

            @Override
            public StewArtUser getById(Long aLong) {
                return null;
            }

            @Override
            public StewArtUser getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends StewArtUser> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends StewArtUser> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends StewArtUser> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<StewArtUser> findAll() {
                return List.of();
            }

            @Override
            public List<StewArtUser> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public <S extends StewArtUser> S save(S entity) {
                return null;
            }

            @Override
            public Optional<StewArtUser> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(StewArtUser entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends StewArtUser> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<StewArtUser> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<StewArtUser> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends StewArtUser> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends StewArtUser> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends StewArtUser> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends StewArtUser> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends StewArtUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        ingredientRepository = new IngredientRepository() {
            @Override
            public Optional<List<Ingredient>> findByIngredientNameContaining(String searchTerm) {
                return Optional.empty();
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Ingredient> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Ingredient> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<Ingredient> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Ingredient getOne(Long aLong) {
                return null;
            }

            @Override
            public Ingredient getById(Long aLong) {
                return null;
            }

            @Override
            public Ingredient getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Ingredient> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends Ingredient> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends Ingredient> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public List<Ingredient> findAll() {
                return List.of();
            }

            @Override
            public List<Ingredient> findAllById(Iterable<Long> longs) {
                return List.of();
            }

            @Override
            public <S extends Ingredient> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Ingredient> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Ingredient entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Ingredient> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<Ingredient> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<Ingredient> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Ingredient> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Ingredient> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Ingredient> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Ingredient> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Ingredient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        recipeController = new RecipeController(recipeRepository,
                                                recipeIngredientRepository,
                                                stewArtUserRepository,
                                                ingredientRepository);
        datamodel = new BindingAwareModelMap();
    }

    @Test
    void validateFormModalHiddenFalse() {
        // Arrange
        datamodel.addAttribute("formModalHidden", false);

        // Act
        recipeController.setupRecipeOverview(datamodel, recipeRepository.findAll(), false);

        // Assert
        assertEquals(false, datamodel.getAttribute("formModalHidden"),
                "FormModalHidden is not false");
    }

    @Test
    void validateDefaultSearchForm() {
        // Arrange

        // Act
        recipeController.setupRecipeOverview(datamodel, recipeRepository.findAll(), true);

        // Assert
        assertTrue(compareRecipe(
                new Recipe(), (Recipe) datamodel.getAttribute("searchForm")),
                "No new searchForm was created");
    }

    @Test
    void validateDefaultFormRecipe() {
        // Arrange

        // Act
        recipeController.setupRecipeOverview(datamodel, recipeRepository.findAll(), true);

        // Assert
        assertTrue(compareRecipeDTO(
                new RecipeDTO(true, ingredientRepository),
                (RecipeDTO) datamodel.getAttribute("formRecipe")),
                "New formRecipe does not match expected format");
    }

    @Test
    void validateSearchFormExisting() {
        // Arrange
        Recipe searchForm = new Recipe();
        searchForm.setRecipeId((long) 12);
        searchForm.setTitle("test for recipe");
        searchForm.setShortDescription("testdescription for recipetest");
        searchForm.setPrepTime(20);
        searchForm.setCookTime(30);
        searchForm.setServings(4);
        searchForm.setInstructions("Just Stew it!");
        searchForm.setIngredients(Set.of(
                new RecipeIngredient(new Ingredient()),
                new RecipeIngredient(60, IngredientUnits.mL, new Ingredient())));
        searchForm.setLikedByUserSet(Set.of(new StewArtUser()));
        searchForm.setRecipeAuthor(new StewArtUser());

        datamodel.addAttribute("searchForm", searchForm);

        // Act
        recipeController.setupRecipeOverview(datamodel, recipeRepository.findAll(), true);

        // Assert
        assertTrue(compareRecipe(
                searchForm, (Recipe) datamodel.getAttribute("searchForm")),
                "SearchForm was overwritten in some way");
    }

    public static boolean compareRecipe(Recipe expected, Recipe actual) {
        if (expected == actual) {
            return true;
        }
        boolean id, title, shortDescription, prep, cook, servings, instructions, image, ingredients, liked, author;

        if (expected.getRecipeAuthor() != null && actual.getRecipeAuthor() != null) {
            author = Objects.equals(expected.getRecipeAuthor().getUsername(), actual.getRecipeAuthor().getUsername());
        } else {
            author = expected.getRecipeAuthor() == null && actual.getRecipeAuthor() == null;
        }

        id = Objects.equals(expected.getRecipeId(), actual.getRecipeId());
        title = Objects.equals(expected.getTitle(), actual.getTitle());
        shortDescription = Objects.equals(expected.getShortDescription(), actual.getShortDescription());
        prep = Objects.equals(expected.getPrepTime(), actual.getPrepTime());
        cook = Objects.equals(expected.getCookTime(), actual.getCookTime());
        servings = Objects.equals(expected.getServings(), actual.getServings());
        instructions = Objects.equals(expected.getInstructions(), actual.getInstructions());
        image = Objects.equals(expected.getImageUrl(), actual.getImageUrl());
        ingredients = Objects.equals(expected.getIngredients(), actual.getIngredients());
        liked = Objects.equals(expected.getLikedByUserSet(), actual.getLikedByUserSet());

        return id
                && title
                && shortDescription
                && prep
                && cook
                && servings
                && instructions
                && image
                && ingredients
                && liked
                && author;
    }

    private static boolean compareRecipeDTO(RecipeDTO expected, RecipeDTO actual) {

        if (expected == actual) {
            return true;
        }

        boolean id, title, shortDescription, prep, cook, servings, instructions, image, allIngredients;

    id = Objects.equals(expected.getRecipeId(), actual.getRecipeId());
    title = Objects.equals(expected.getTitle(), actual.getTitle());
    shortDescription = Objects.equals(expected.getShortDescription(), actual.getShortDescription());
    prep = Objects.equals(expected.getPrepTime(), actual.getPrepTime());
    cook = Objects.equals(expected.getCookTime(), actual.getCookTime());
    servings = Objects.equals(expected.getServings(), actual.getServings());
    instructions = Objects.equals(expected.getInstructions(), actual.getInstructions());
    image = Objects.equals(expected.getImageUrl(), actual.getImageUrl());
    allIngredients = Objects.equals(expected.getAllIngredients(), actual.getAllIngredients());

        return id
                && title
                && shortDescription
                && prep
                && cook
                && servings
                && instructions
                && image
                && allIngredients;
    }
}
