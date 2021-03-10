package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class UIManager {
    public static Stage stage;
    protected static ArrayList<Recipe> recipes = new ArrayList<>();
    protected static ArrayList<Screen> scenes = new ArrayList<>();
    protected static int currentSceneIndex;
    protected static BorderPane root;


    public UIManager()
    {
        currentSceneIndex = -1;
        root = new BorderPane();

    }



    protected HBox createTopUI()
    {
        HBox topUI = new HBox();
        // Creates the search bar at the top of the UI.
        String searchText = "";
        Label recipesLabel = new Label("Recipes");
        TextField searchBar = new TextField();
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(event -> {
            System.out.println(searchBar.getText());
            for(int i = 0; i < recipes.size(); i++)
            {
                System.out.println(recipes.get(i).getName() + ": " + recipes.get(i).compareTo(new Recipe(""), searchBar.getText()));
            }

            recipes.sort((o1, o2)
                    -> o1.compareTo(
                    o2, searchBar.getText()));

            scenes.get(0).updateUI();
            if(currentSceneIndex != 0)
            {
                showScene(0);
            }


            searchBar.clear();
        });

        topUI.getChildren().addAll(recipesLabel, searchBar, searchBtn);
        topUI.setPadding(new Insets(20, 5, 0,100));
        topUI.setSpacing(5);

        return topUI;
    }


    private VBox createLeftUI()
    {
        // Creates the pane for this side.
        VBox leftUI = new VBox();

        // Creates the buttons on the left side of the UI.
        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> {
            showScene(0);
        });

        /*
        // Change this to add recipe button later.
        Button viewRecipeBtn = new Button("View Recipe");
        viewRecipeBtn.setOnAction(event -> {
            showScene(1);
        });


         */

        Button changeRecipeBtn = new Button("Change Recipe");
        changeRecipeBtn.setOnAction(event -> {
            // Only works if on Recipe Overview screen.
            if(currentSceneIndex == 1)
            {
                scenes.get(2).setRecipe(scenes.get(1).getRecipe());
                showScene(2);
            }

        });

        Button newRecipeBtn = new Button("New Recipe");
        newRecipeBtn.setOnAction(event -> {
            Recipe newRecipe = new Recipe("");
            newRecipe.setProcedures(new ArrayList<String>(1));
            newRecipe.setIngredients(new ArrayList<Ingredient>(1));
            addRecipe(newRecipe);
            scenes.get(2).setRecipe(newRecipe);
            showScene(2);

        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(event -> {
            deleteRecipe(recipes.size() - 1);
        });

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            if(getCurrentSceneIndex() == 0)
            {
                // Save all recipes if on home screen
                // Add a recipe to the home scene UI
                addRecipe(new Recipe("New recipe!"));
            }
            else if(getCurrentSceneIndex() == 2)
            {
                // Save recipe that is currently being changed if on change recipe screen.
                scenes.get(2).saveRecipe();
                showScene(0);
            }

        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> {
            showScene(0);
        });
        Button quitBtn = new Button("Quit");
        quitBtn.setOnAction(event -> {
            if(currentSceneIndex == 0)
            {
                Platform.exit();
            }
            else
            {
                showScene(0);
            }
        });

        // Assigns functionality to the buttons.
        leftUI.getChildren().addAll(homeBtn, changeRecipeBtn, newRecipeBtn, deleteBtn, saveBtn, quitBtn);
        leftUI.setSpacing(10);

        return leftUI;
    }


    protected void refreshGeneralUI()
    {
        root.setTop(createTopUI());
        root.setLeft(createLeftUI());
    }


    public void show()
    {
        refreshGeneralUI();
        showScene(0);

        stage.setTitle("IA " + scenes.get(currentSceneIndex).getName() + " Screen");
        stage.setScene(new javafx.scene.Scene(root, 800, 800));
        stage.show();
    }


    public void addRecipe(Recipe recipeToBeAdded)
    {
        recipes.add(recipeToBeAdded);
        saveRecipes();
        scenes.get(0).updateUI();

    }

    public void deleteRecipe(int deleteIndex)
    {
        recipes.remove(deleteIndex);
        saveRecipes();
        scenes.get(0).updateUI();
    }

    public void deleteRecipe(Object objectForRemoval)
    {
        recipes.remove(objectForRemoval);
        saveRecipes();
        scenes.get(0).updateUI();
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


    public void openRecipeOverview(Recipe recipe)
    {
        scenes.get(1).openRecipe(recipe);
        showScene(1);
    }



    public void addScene(Screen newScene)
    {
        scenes.add(newScene);
    }

    // Shows the scene at a given index in the scene array list.
    // 0    - Home
    // 1    - Recipe Overview
    // 2    - Change Recipe
    public static void showScene(int sceneIndex)
    {
        if(sceneIndex != currentSceneIndex)
        {
            if(sceneIndex < scenes.size() && sceneIndex >= 0)
            {
                scenes.get(sceneIndex).runBirthMethods();
                scenes.get(sceneIndex).updateUI();
                root.setCenter(scenes.get(sceneIndex).getUI());
                currentSceneIndex = sceneIndex;
                stage.setTitle(scenes.get(currentSceneIndex).getTitle());

            }
            else
            {
                System.out.println("Scene index out of bounds.");
            }
        }
        else
        {
            System.out.println("Already on scene " + sceneIndex);
        }
    }

    public int getCurrentSceneIndex() {
        return currentSceneIndex;
    }

    public String toString()
    {
        String allScenes = "";
        for(int i = 0; i < scenes.size(); i++)
        {
            allScenes += i + ". " + scenes.get(i).toString() + "\n";
        }
        return allScenes;
    }



}
