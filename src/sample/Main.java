package sample;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

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


        UI.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
