package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import java.util.*;

import org.apache.commons.lang3.StringUtils;



public class UIManager {
    public static Stage stage;
    protected static ArrayList<Recipe> recipes = new ArrayList<>();
    protected static ArrayList<Screen> scenes = new ArrayList<>();
    private ArrayList<Node> homeNodes = new ArrayList<>();
    private ArrayList<Node> overviewNodes = new ArrayList<>();
    private ArrayList<Node> changeRecipeNodes = new ArrayList<>();
    protected static int currentSceneIndex;
    protected static BorderPane root;


    public UIManager()
    {
        currentSceneIndex = -1;
        root = new BorderPane();
        // Creates the buttons on the left side of the UI.
        Button homeBtn = new Button("Home");
        homeBtn.setOnAction(event -> {
            showScene(0);
        });

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
            deleteRecipe(scenes.get(currentSceneIndex).getRecipe());
            showScene(0);
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
        homeNodes = new ArrayList<>(Arrays.asList(homeBtn, newRecipeBtn, quitBtn));
        overviewNodes = new ArrayList<>(Arrays.asList(homeBtn, newRecipeBtn, changeRecipeBtn, deleteBtn, saveBtn, cancelBtn));
        changeRecipeNodes = new ArrayList<>(Arrays.asList(homeBtn, saveBtn, cancelBtn));
    }



    protected HBox createTopUI()
    {
        HBox topUI = new HBox();
        // Creates the search bar at the top of the UI.
        String searchText = "";
        Label recipesLabel = new Label("Recipes");
        TextField searchBar = new TextField();
        searchBar.setOnAction(event -> {
            showScene(0);
            searchRecipes(searchBar);
        });
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(event -> {
            showScene(0);
            searchRecipes(searchBar);
        });

        topUI.getChildren().addAll(recipesLabel, searchBar, searchBtn);
        topUI.setPadding(new Insets(20, 5, 0,100));
        topUI.setSpacing(5);

        return topUI;
    }


    private VBox createLeftUI(ArrayList<Node> nodes)
    {
        // Creates the pane for this side.
        VBox leftUI = new VBox();



        // Assigns functionality to the buttons.
        for(Node node : nodes)
        {
            leftUI.getChildren().add(node);
        }

        leftUI.setSpacing(10);

        return leftUI;
    }

    private void searchRecipes(TextField searchBar)
    {

        recipes.sort((o1, o2)
                -> o1.compareTo(o2, searchBar.getText()));
        scenes.get(0).updateUI();
        if(currentSceneIndex != 0)
        {
            showScene(0);
        }
    }

    protected void refreshGeneralUI()
    {
        root.setTop(createTopUI());
        if(currentSceneIndex == 0)
        {
            root.setLeft(createLeftUI(homeNodes));
        }
        else if(currentSceneIndex == 1)
        {
            root.setLeft(createLeftUI(overviewNodes));
        }
        else
        {
            root.setLeft(createLeftUI(changeRecipeNodes));
        }

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
    public void showScene(int sceneIndex)
    {
        if(sceneIndex != currentSceneIndex)
        {
            if(sceneIndex < scenes.size() && sceneIndex >= 0)
            {
                currentSceneIndex = sceneIndex;
                refreshGeneralUI();
                scenes.get(sceneIndex).runBirthMethods();
                scenes.get(sceneIndex).updateUI();
                root.setCenter(scenes.get(sceneIndex).getUI());
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
