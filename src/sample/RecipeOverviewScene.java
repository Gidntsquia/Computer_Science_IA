package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RecipeOverviewScene extends Screen{
    private Recipe currentRecipe;

    public RecipeOverviewScene(String screenName) {
        super(screenName);
        BorderPane recipeOverviewScene = new BorderPane();
        VBox center = new VBox();

        // Accesses screenpane from Screen class
        Label overviewLabel = new Label("Overview:");

        // Sets current recipe to a default value.
        currentRecipe = new Recipe("[Recipe Name]");

        setUI(recipeOverviewScene);
    }

    @Override
    public void openRecipe(Recipe recipe)
    {
        currentRecipe = recipe;
        // Sets most recent access day to today.
        recipe.setDaysSinceLastAccess(0);

        BorderPane overviewUI = new BorderPane();
        VBox mainUI = new VBox();


        Label recipeSummary = new Label(recipe.getOverview() + "\n");
        Label ingredientList = new Label("Ingredients\n" + recipe.getIngredientList() + "\n");
        Label procedureList = new Label("Procedures\n" + recipe.getProcedureList() + "\n");

        mainUI.getChildren().addAll(new Label("Overview"), recipeSummary, ingredientList, procedureList);

        overviewUI.setLeft(mainUI);

        setUI(overviewUI);

    }

    @Override
    public String getTitle()
    {
        return "IA " + this.getName() + " Screen. Currently viewing: " + currentRecipe.getName();
    }

    @Override
    public Recipe getRecipe()
    {
        return this.currentRecipe;
    }


}
