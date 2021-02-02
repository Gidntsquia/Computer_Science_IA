package sample;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Screen.stage = primaryStage;
        BorderPane root = new BorderPane();
        //UIManager UI = new UIManager();

        HomeScene homeScene = new HomeScene("Home");
        RecipeOverviewScene recipeOverviewScene = new RecipeOverviewScene("Recipe Overview");
        ChangeRecipeScene changeRecipeScene = new ChangeRecipeScene("Change Recipe");

        //UI.addScene(homeScene);
        //UI.addScene(recipeOverviewScene);
        //UI.addScene(changeRecipeScene);

        //System.out.println(UI);
        //UI.showScene(2);

        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Spaghetti"));

        changeRecipeScene.show();
        homeScene.homeBtn.setOnAction(event -> {
            homeScene.show();
            System.out.println("Switching to home.");

        });

        homeScene.addRecipeBtn.setOnAction(event -> {
            changeRecipeScene.show();
            System.out.println("Switching to recipe changer.");
        });

        homeScene.changeRecipeBtn.setOnAction(event -> {
            recipeOverviewScene.show();
            System.out.println("Switching to recipe changer.");
        });


        recipeOverviewScene.homeBtn.setOnAction(event -> {
            homeScene.show();
            System.out.println("Switching to home.");

        });

        recipeOverviewScene.addRecipeBtn.setOnAction(event -> {
            changeRecipeScene.show();
            System.out.println("Switching to recipe changer.");
        });

        recipeOverviewScene.changeRecipeBtn.setOnAction(event -> {
            recipeOverviewScene.show();
            System.out.println("Switching to recipe changer.");
        });



        changeRecipeScene.homeBtn.setOnAction(event -> {
            homeScene.show();
            System.out.println("Switching to home.");

        });

        changeRecipeScene.addRecipeBtn.setOnAction(event -> {
            changeRecipeScene.show();
            System.out.println("Switching to recipe changer.");
        });

        changeRecipeScene.changeRecipeBtn.setOnAction(event -> {
            recipeOverviewScene.show();
            System.out.println("Switching to recipe changer.");
        });


        //homeScene.show();

        try{
            Writer w = new FileWriter("output.txt", false);
            w.write("Yo\n");
            w.close();

        }catch(Exception myException) {System.out.println(myException);}

        try{
            Scanner s = new Scanner(new File("output.txt"));
            while(s.hasNextLine())
            {
                System.out.println(s.nextLine());
            }
        }catch(Exception myException){System.out.println(myException);}









        Ingredient potato = new Ingredient("Potato", 5, "cups", "starchy");
        Ingredient tomato = new Ingredient("Tomato", 2, "cups", "acidic");

        System.out.println(potato);


    }

    public static void main(String[] args) {
        launch(args);
    }

}
