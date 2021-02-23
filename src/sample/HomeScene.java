package sample;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeScene extends Screen{

    public static ArrayList<Recipe> recipes = new ArrayList<>();
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

    // Save recipe, download recipes
    // Get list of recipes +  recipe maker, make it where you can save recipes

    // This code will: Use a for loop to iterate through the recipes Array List.
    // It will add each Recipe.toString to a String. A label will be initialized with this String
    // and added to the center of the homescene. Then, we update the screenpane to be exactly like the homescene.
    public void showRecipes()
    {
        String recipeList = "";
        for(Recipe recipe : recipes)
        {
            recipeList += recipe.toString() + "\n\n";
        }

        homeScene.setCenter(new Label(recipeList));

        //screenPane = homeScene;
    }

    @Override
    public void addRecipe(Recipe recipeToBeAdded)
    {
        recipes.add(recipeToBeAdded);
        saveRecipes();
        //downloadRecipes();
        showRecipes();
    }

    @Override
    public void deleteRecipe()
    {
        recipes.remove(recipes.size() - 1);
        saveRecipes();
        showRecipes();
    }

    private void downloadRecipes()
    {
        try{
            Scanner s = new Scanner(new File("output.txt"));
            while(s.hasNextLine())
            {
                System.out.println(s.nextLine());
            }
        }catch(Exception myException){System.out.println(myException);}
    }

    private void saveRecipes()
    {
        try{
            Writer w = new FileWriter("output.txt", false);
            for(Recipe recipe : recipes)
            {
                w.write(recipe.toString() + "\n");
            }
            w.close();

        }catch(Exception myException) {System.out.println(myException);}
    }



}
