package nl.miwnn.se14.StewArt.stewart.unittests;

import nl.miwnn.se14.StewArt.stewart.controller.HomepageController;
import nl.miwnn.se14.StewArt.stewart.enums.IngredientUnits;
import nl.miwnn.se14.StewArt.stewart.model.Ingredient;
import nl.miwnn.se14.StewArt.stewart.model.Recipe;
import nl.miwnn.se14.StewArt.stewart.model.RecipeIngredient;
import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import nl.miwnn.se14.StewArt.stewart.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luc Weerts
 * Tests HomepageController
 */
public class HomepageControllerTests {
    private RecipeRepository recipeRepository;
    private HomepageController homepageController;
    private Model datamodel;

    @BeforeEach()
    void beforeEach() {
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
        homepageController = new HomepageController(recipeRepository);
        datamodel = new BindingAwareModelMap();
    }

    @Test
    void validateFormModalHiddenFalse() {
        // Arrange
        datamodel.addAttribute("formModalHidden", false);

        // Act
        homepageController.setupHomepage(datamodel);

        // Assert
        assertEquals(false, datamodel.getAttribute("formModalHidden"),
                "FormModalHidden is not false");
    }

    @Test
    void validateFormModalHiddenDefault() {
        // Arrange

        // Act
        homepageController.setupHomepage(datamodel);

        // Assert
        assertEquals(true, datamodel.getAttribute("formModalHidden"),
                "FormModalHidden should be true by default");
    }

    @Test
    void validateSearchFormDefault() {
        // Arrange

        // Act
        homepageController.setupHomepage(datamodel);

        // Assert
        assertTrue(RecipeControllerTests.compareRecipe(
                new Recipe(), (Recipe) datamodel.getAttribute("searchForm")));
    }

    @Test
    void validateSearchFormExisting() {
        // Arrange
        Recipe searchForm = new Recipe();
        searchForm.setRecipeId((long) 12);
        searchForm.setTitle("test recipe");
        searchForm.setShortDescription("Just a description");
        searchForm.setPrepTime(20);
        searchForm.setCookTime(30);
        searchForm.setInstructions("Just do it!");
        searchForm.setIngredients(Set.of(new RecipeIngredient(new Ingredient()), new RecipeIngredient(60, IngredientUnits.mL, new Ingredient())));
        searchForm.setLikedByUserSet(Set.of(new StewArtUser()));
        searchForm.setRecipeAuthor(new StewArtUser());

        // Act
        homepageController.setupHomepage(datamodel);

        // Assert
        assertTrue(RecipeControllerTests.compareRecipe(
                new Recipe(), (Recipe) datamodel.getAttribute("searchForm")));
    }
}
