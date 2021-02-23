package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UIManager {
    public static Stage stage;
    private ArrayList<Screen> scenes = new ArrayList<>();
    private BorderPane root;
    private Screen currentScene;
    private int currentSceneIndex;


    public UIManager()
    {
        currentSceneIndex = -1;
        root = new BorderPane();



    }



    private HBox createTopUI()
    {
        HBox topUI = new HBox();
        // Creates the search bar at the top of the UI.
        String searchText = "";
        Label recipesLabel = new Label("Recipes");
        TextField searchBar = new TextField();
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(event -> {
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

        // Change this to add recipe button later.
        Button viewRecipeBtn = new Button("View Recipe");
        viewRecipeBtn.setOnAction(event -> {
            showScene(1);
        });

        Button changeRecipeBtn = new Button("Change Recipe");
        changeRecipeBtn.setOnAction(event -> {
            showScene(2);
        });

        Button newRecipeBtn = new Button("New Recipe");
        newRecipeBtn.setOnAction(event -> {
            showScene(2);
        });

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(event -> {
            scenes.get(0).deleteRecipe();
        });

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(event -> {
            // Add a recipe to the home scene UI
            scenes.get(0).addRecipe(new Recipe("New recipe!"));
        });
        Button cancelBtn = new Button("Cancel");
        Button quitBtn = new Button("Quit");

        // Assigns functionality to the buttons.
        leftUI.getChildren().addAll(homeBtn, viewRecipeBtn, changeRecipeBtn, newRecipeBtn, deleteBtn, saveBtn, quitBtn);
        leftUI.setSpacing(10);

        return leftUI;
    }


    public void show()
    {
        root.setTop(createTopUI());
        root.setLeft(createLeftUI());
        showScene(0);

        stage.setTitle("IA " + scenes.get(currentSceneIndex).getName() + " Screen");
        stage.setScene(new javafx.scene.Scene(root, 800, 800));
        stage.show();
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
                root.setCenter(scenes.get(sceneIndex).getUI());
                currentSceneIndex = sceneIndex;
                stage.setTitle("IA " + scenes.get(currentSceneIndex).getName() + " Screen");
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
