package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        BorderPane overviewUI = new BorderPane();
        VBox mainUI = new VBox();
        Label overviewLabel = new Label("Overview");
        String summaryText = "Using ";
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<String> procedures = recipe.getMethods();

        // Iterates through all ingredients, adding each ingredient's name to the string representation of ingredients.
        // Hard coded functionality for if there are only 1 or 2 ingredients.
        if(ingredients.size() == 1)
        {
            summaryText += ingredients.get(0).getName().toLowerCase() + "s, ";
        }
        else if(ingredients.size() == 2)
        {
            summaryText += ingredients.get(0).getName().toLowerCase() + "s ";
            summaryText += "and " + ingredients.get(1).getName().toLowerCase() + "s, ";
        }
        else
        {
            for(int i = 0; i < ingredients.size(); i++)
            {
                if(i == ingredients.size() - 1)
                {
                    summaryText += "and " + ingredients.get(i).getName().toLowerCase() + "s, ";
                }
                else
                {
                    summaryText += ingredients.get(i).getName().toLowerCase() + "s, ";
                }


            }
        }
        // Iterates through all procedures, adding each procedure's name to the string representation of procedures.
        // Hard coded functionality for if there are only 1 or 2 procedures.
        if(procedures.size() == 1)
        {
            summaryText += procedures.get(0).toLowerCase() + ". ";
        }
        else if(procedures.size() == 2)
        {
            summaryText += procedures.get(0).toLowerCase() + " ";
            summaryText += "and then " + procedures.get(1).toLowerCase() + ".";
        }
        else
        {
            for(int i = 0; i < procedures.size(); i++)
            {
                if(i == procedures.size() - 1)
                {
                    summaryText += "and then " + procedures.get(i).toLowerCase() + ".";
                }
                else
                {
                    summaryText += procedures.get(i).toLowerCase() + ", ";
                }
            }
        }



        Label recipeSummary = new Label(summaryText + "\n");
        Label ingredientList = new Label(recipe.getIngredientList() + "\n");
        Label procedureList = new Label(recipe.getMethodList() + "\n");

        mainUI.getChildren().addAll(recipeSummary, ingredientList, procedureList);

        overviewUI.setLeft(mainUI);

        setUI(overviewUI);

    }

    @Override
    public String getTitle()
    {
        return "IA " + this.getName() + " Screen. Currently viewing: " + currentRecipe.getName();
    }


}
