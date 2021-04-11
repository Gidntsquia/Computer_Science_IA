package sample;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RecipeOverviewScene extends Screen{
    private Recipe currentRecipe;

    public RecipeOverviewScene(String screenName) {
        super(screenName);
        BorderPane recipeOverviewScene = new BorderPane();
        VBox center = new VBox();
        // Sets UI for this scene with a method from Screen super class.
        Label overviewLabel = new Label("Overview:");
        // Sets current recipe to a filler value.
        currentRecipe = new Recipe("[Recipe Name]");
        setUI(recipeOverviewScene);
    }

    // Displays details of recipe to UI.
    @Override
    public void openRecipe(Recipe recipe)
    {
        currentRecipe = recipe;
        // Sets most recent access day to today.
        recipe.updateLastAccessToNow();
        saveRecipes();

        BorderPane overviewUI = new BorderPane();
        VBox summaryUI = new VBox();
        VBox mainUI = new VBox();
        HBox defaultServedUI = new HBox();

        Label recipeSummary = new Label(recipe.getOverview() + "\n");
        recipeSummary.setWrapText(true);
        recipeSummary.setMaxWidth(450);
        summaryUI.getChildren().addAll(bold(new Label("Overview")), recipeSummary);

        Label ingredientList = new Label(recipe.getIngredientList());
        Label procedureList = new Label(recipe.getProcedureList());

        mainUI.getChildren().addAll(new Label("\n"), bold(new Label("Ingredients")), ingredientList,
                new Label("\n\n"), bold(new Label("Procedures")), procedureList);
        mainUI.setSpacing(0);
        TextField defaultServedField = new TextField(String.valueOf(recipe.getCurrentlySurves()));
        defaultServedField.setOnAction(event -> {
            for(int i = 0; i < currentRecipe.getIngredients().size(); i++)
            {
                Ingredient tempIngredient =  currentRecipe.getIngredients().get(i);
                // New amount is the default amount times the ratio of the normal people served to the new people served.
                tempIngredient.multiplyQuantity(
                        Double.parseDouble(defaultServedField.getText()) / recipe.getDefaultServed());
            }
            recipe.setCurrentlySurves(Integer.parseInt(defaultServedField.getText()));
            openRecipe(currentRecipe);
        });
        defaultServedUI.getChildren().addAll(new Label("Serves:"), defaultServedField);
        overviewUI.setTop(summaryUI);
        overviewUI.setLeft(mainUI);
        overviewUI.setRight(defaultServedUI);

        setUI(overviewUI);
    }

    public Label bold(Label labelForBolding)
    {
        labelForBolding.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, Font.getDefault().getSize()));
        return labelForBolding;
    }
    @Override
    public String getTitle()
    {
        return this.getName() + " Screen. Currently viewing: " + currentRecipe.getName();
    }

    @Override
    public Recipe getRecipe()
    {
        return this.currentRecipe;
    }





}
