package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// This is the base class for all scenes used in this project.
// It has the basics for creating, modifying, and managing a scene.
// It also includes anything that all the scenes have in common.
public class Screen {
    public static Stage stage;
    public BorderPane screenPane;
    private String name;
    private VBox leftButtonUI;
    private HBox searchBarUI;
    public Button homeBtn;
    public Button addRecipeBtn;
    public Button changeRecipeBtn;

    public Screen(String screenName)
    {
        // Creates the panes for this scene.
        screenPane = new BorderPane();
        searchBarUI = new HBox();

        // Assigns screenName parameter to saved variable for later use.
        name = screenName;

        // Creates the search bar at the top of the UI.
        Label recipesLabel = new Label("Recipes");
        TextField searchBar = new TextField();
        Button searchBtn = new Button("Search");
        searchBarUI.getChildren().addAll(recipesLabel, searchBar, searchBtn);
        searchBarUI.setPadding(new Insets(20, 5, 0,100));
        searchBarUI.setSpacing(5);
        screenPane.setTop(searchBarUI);

        // Debug- allows you to see what screen the code thinks its on.
        Label Title = new Label(name);
        screenPane.setBottom(Title);



        screenPane.setLeft(createLeftButtonUI());
        createCenter();


    }

    public void createCenter()
    {
        System.out.println("Sample text");
    }

    public VBox createLeftButtonUI()
    {
        // Creates the pane for this side.
        leftButtonUI = new VBox();

        // Creates the buttons on the left side of the UI.
        homeBtn = new Button("Home");
        addRecipeBtn = new Button("Add Recipe");
        changeRecipeBtn = new Button("Change Recipe");
        Button deleteBtn = new Button("Delete");
        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");
        Button quitBtn = new Button("Quit");

        // Assigns functionality to the buttons.
        leftButtonUI.getChildren().addAll(homeBtn, addRecipeBtn, changeRecipeBtn, deleteBtn, saveBtn, quitBtn);
        leftButtonUI.setSpacing(10);

        return leftButtonUI;
    }

    public static Stage getStage() {
        return stage;
    }

    public void show()
    {
        stage.setTitle("IA " + name + " Screen");
        stage.setScene(new javafx.scene.Scene(screenPane, 800, 800));
        stage.show();

    }

    public String toString()
    {
        return name;
    }



}
