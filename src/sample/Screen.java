package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public BorderPane screenPane;
    private String name;

    public Screen(String screenName)
    {
        // Creates the panes for this scene.
        this.screenPane = new BorderPane();
        name = screenName;


    }

    public BorderPane getUI()
    {
        return screenPane;
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
