package sample;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.*;

import static sample.UIManager.recipes;
import java.time.*;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try{
            File recipesFile = new File("recipes.txt");
            File ingredientsFile = new File("ingredients.txt");
            if(!recipesFile.isFile())
            {
                System.out.println("Recipe file created");
                recipesFile.createNewFile();
            }
            if(!ingredientsFile.isFile())
            {

                System.out.println("Ingredients file created");
                ingredientsFile.createNewFile();
            }
        }catch (Exception e){System.out.println(e);}
        Screen.stage = primaryStage;
        UIManager.stage = primaryStage;
        BorderPane root = new BorderPane();
        // UI Manager with top and left button UI
        // Screens that only change the middle of this UI
        UIManager UI = new UIManager();

        HomeScene homeScene = new HomeScene("Home");
        RecipeOverviewScene recipeOverviewScene = new RecipeOverviewScene("Recipe Overview");
        ChangeRecipeScene changeRecipeScene = new ChangeRecipeScene("Change Recipe");
        ChangeIngredientScene changeIngredientScene = new ChangeIngredientScene("Change Ingredient");

        UI.addScene(homeScene);
        UI.addScene(recipeOverviewScene);
        UI.addScene(changeRecipeScene);
        UI.addScene(changeIngredientScene);

        Ingredient potato = new Ingredient("Potato", 5, "cups", "starchy");
        Ingredient tomato = new Ingredient("Tomato", 2, "cups", "acidic");
        /*
        Ingredient.addIngredientToList(potato);
        Ingredient.addIngredientToList(tomato);
         */
        ArrayList<Ingredient> basicIngredients = new ArrayList<>();
        basicIngredients.add(potato);
        basicIngredients.add(tomato);



        String method1 = "Skin the potato";
        String method2 = "Wash the tomato";
        String method3 = "Put it all together";
        ArrayList<String> basicMethods = new ArrayList<>();
        basicMethods.add(method1);
        basicMethods.add(method2);
        basicMethods.add(method3);

        LocalDateTime time = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Hello");
        LocalDateTime time2 = LocalDateTime.now();
        System.out.println(time2.compareTo(time));

        //UI.addRecipe(new Recipe("Recipe 1", new ArrayList<>(basicIngredients), new ArrayList<>(basicMethods), 10, 0));
        //UI.addRecipe(new Recipe("Recipe 2", new ArrayList<>(basicIngredients), new ArrayList<>(basicMethods), 1, 5));
        //UI.addRecipe(new Recipe("Yo", new ArrayList<>(basicIngredients), new ArrayList<>(basicMethods), 100, 27));

        UI.show();











        /*
        Ingredient potato = new Ingredient("Potato", 5, "cups", "starchy");
        Ingredient tomato = new Ingredient("Tomato", 2, "cups", "acidic");

        System.out.println(potato);
        */

    }

    public static void main(String[] args) {
        launch(args);
    }

}
