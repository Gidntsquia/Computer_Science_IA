package sample;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import static sample.UIManager.recipes;

public class HomeScene extends Screen{

    private BorderPane homeScene;

    public HomeScene(String screenName) {
        super(screenName);
        homeScene = new BorderPane();

        // TODO add download recipes from file feature here
        recipes.clear();
        recipes.add(new Recipe("Recipe 1"));
        recipes.add(new Recipe("Recipe 2"));
        // Accesses screenpane from Screen class
        showRecipes();

        setUI(homeScene);

    }

    @Override
    public void updateUI()
    {
        showRecipes();
    }
    // Save recipe, download recipes
    // Get list of recipes +  recipe maker, make it where you can save recipes

    // This code will: Use a for loop to iterate through the recipes Array List.
    // It will add each Recipe.toString to a String. A label will be initialized with this String
    // and added to the center of the homescene. Then, we update the screenpane to be exactly like the homescene.
    public void showRecipes()
    {
        VBox recipeList = new VBox();
        recipeList.setSpacing(20);
        //String recipeList = "";
        for(Recipe recipe : recipes)
        {
            recipeList.getChildren().add(recipe.getRecipeBoxUI());
            // Make this a UI box that gets added to a VBox

            //    recipeList += recipe.toString() + "\n\n";
        }

        homeScene.setCenter(recipeList);

        //screenPane = homeScene;
    }






}
