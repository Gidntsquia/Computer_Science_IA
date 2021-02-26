package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// This is the base class for all scenes used in this project.
// It has the basics for creating, modifying, and managing a scene.
// It also includes anything that all the scenes have in common.
public class Screen {
    public static Stage stage;
    public ScrollPane screenPane;
    private String name;

    public Screen(String screenName)
    {
        // Creates the panes for this scene.
        this.screenPane = new ScrollPane();
        name = screenName;


    }

    public void updateUI()
    {
        // This command is empty because it is overriden by the child classes.

    }

    public void openRecipe(Recipe recipe)
    {
        // This command is empty because it is overriden by the child classes.
    }


    public ScrollPane getUI()
    {
        return screenPane;
    }

    public void setUI(BorderPane inputPane)
    {
        screenPane.setContent(inputPane);
    }

    public static Stage getStage() {
        return stage;
    }

    public String getName() {
        return name;
    }

    public String toString()
    {
        return name;
    }



}
