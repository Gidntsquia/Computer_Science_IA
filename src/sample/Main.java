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
        UIManager.stage = primaryStage;
        BorderPane root = new BorderPane();
        // UI Manager with top and left button UI
        // Screens that only change the middle of this UI
        UIManager UI = new UIManager();

        HomeScene homeScene = new HomeScene("Home");
        RecipeOverviewScene recipeOverviewScene = new RecipeOverviewScene("Recipe Overview");
        ChangeRecipeScene changeRecipeScene = new ChangeRecipeScene("Change Recipe");

        UI.addScene(homeScene);
        UI.addScene(recipeOverviewScene);
        UI.addScene(changeRecipeScene);

        homeScene.addRecipe(new Recipe("Yo"));

        System.out.println("Yoojvhgjooo");
        UI.show();

        //primaryStage.setTitle("IA " + "Home" + " Screen");
        //primaryStage.setScene(new javafx.scene.Scene(root, 800, 800));
        //primaryStage.show();







        /*
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
        */

    }

    public static void main(String[] args) {
        launch(args);
    }

}
