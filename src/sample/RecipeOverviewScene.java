package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RecipeOverviewScene extends Screen{

    public RecipeOverviewScene(String screenName) {
        super(screenName);
        BorderPane recipeOverviewScene = new BorderPane();
        VBox center = new VBox();

        // Accesses screenpane from Screen class
        Label overviewLabel = new Label("Overview:");

        Recipe sampleRecipe = new Recipe("[Recipe Name]");
        Label sampleRecipeLabel = new Label(sampleRecipe.toString());

        center.getChildren().addAll(overviewLabel, sampleRecipeLabel);
        recipeOverviewScene.setCenter(center);

        setUI(recipeOverviewScene);
    }

    private void openRecipe(Recipe recipe)
    {
        BorderPane overviewUI = new BorderPane();
        VBox mainUI = new VBox();
        Label overviewLabel = new Label("Overview");
        String summaryText = "Using ";
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<String> procedures = recipe.getMethods();

        for(int i = 0; i < ingredients.size(); i++)
        {
            if(i == ingredients.size() - 1)
            {
                summaryText += " and " + ingredients.get(i).getName() + ", ";
            }
            else
            {
                summaryText += ingredients.get(i).getName() + ", ";
            }
        }
        for(int i = 0; i < procedures.size(); i++)
        {
            if(i == procedures.size() - 1)
            {
                summaryText += " and then " + procedures.get(i) + ".";
            }
            else
            {
                summaryText += procedures.get(i) + ", ";
            }
        }
        summaryText += "\n";


        Label recipeSummary = new Label(summaryText);
        Label ingredientList = new Label(recipe.getIngredientList());
        Label procedureList = new Label(recipe.getMethodList());

    }



}
