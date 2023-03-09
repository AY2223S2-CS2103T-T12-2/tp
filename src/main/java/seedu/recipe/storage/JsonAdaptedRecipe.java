package seedu.recipe.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Description;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedRecipe {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String title;
    private final String desc;

    // Data fields
    private final Set<JsonAdaptedIngredient> ingredients = new HashSet<>();
    private final Set<JsonAdaptedStep> steps = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("title") Title title, @JsonProperty("desc") Description desc,
            @JsonProperty("ingredients") Set<JsonAdaptedIngredient> ingredients, @JsonProperty("steps") Set<JsonAdaptedStep> steps) {
        this.title = title.title;
        this.desc = desc.description;
        this.ingredients.addAll(ingredients);
        this.steps.addAll(steps);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedRecipe(Recipe source) {
        title = source.getTitle().title;
        desc = source.getDesc().description;
        ingredients.addAll(source.getIngredients().stream()
                      .map(JsonAdaptedIngredient::new)
                      .collect(Collectors.toSet()));
        steps.addAll(source.getSteps().stream()
                .map(JsonAdaptedStep::new)
                .collect(Collectors.toSet()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Recipe} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Recipe toModelType() throws IllegalValueException {
        final Set<Ingredient> recipeIngredients = new HashSet<>();
        for (JsonAdaptedIngredient ingredient : ingredients) {
            recipeIngredients.add(ingredient.toModelType());
        }
        
        final Set<Step> recipeSteps = new HashSet<>();
        for (JsonAdaptedStep step : steps) {
            recipeSteps.add(step.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (desc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDesc(desc)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(desc);
        
        return new Recipe(modelTitle, modelDesc, recipeIngredients, recipeSteps);
    }

}
