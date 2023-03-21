package seedu.recipe.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    public static final String DEFAULT_TITLE = "A Recipe";
    public static final String DEFAULT_DESCRIPTION = "A Description";
    public static final List<Ingredient> DEFAULT_INGREDIENTS = Arrays.asList(
            new Ingredient("eggs"), new Ingredient("flour"));
    public static final List<Step> DEFAULT_STEPS = Arrays.asList(
            new Step("step 1"), new Step("step 2"));

    private Title title;
    private Description desc;
    private Set<Ingredient> ingredients;
    private List<Step> steps;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        title = new Title(DEFAULT_TITLE);
        desc = new Description(DEFAULT_DESCRIPTION);
        ingredients = new HashSet<Ingredient>(DEFAULT_INGREDIENTS);
        steps = new ArrayList<Step>(DEFAULT_STEPS);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        title = recipeToCopy.getTitle();
        desc = recipeToCopy.getDesc();
        ingredients = recipeToCopy.getIngredients();
        steps = recipeToCopy.getSteps();
    }

    /**
     * Sets the {@code Title} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withDesc(String desc) {
        this.desc = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code Ingredients} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withIngredients(String... ingredients) {
        Set<Ingredient> ingredientSet = Stream.of(ingredients).map(Ingredient::new).collect(Collectors.toSet());
        this.ingredients = ingredientSet;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public RecipeBuilder withSteps(String... steps) {
        List<Step> stepList = Stream.of(steps).map(Step::new).collect(Collectors.toList());
        this.steps = stepList;
        return this;
    }

    public Recipe build() {
        return new Recipe(title, desc, ingredients, steps);
    }

}
