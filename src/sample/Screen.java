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
public class Screen extends UIManager{
    public static Stage stage;
    public ScrollPane screenPane;
    protected String name;

    public Screen(String screenName)
    {
        // Creates the panes for this scene.
        this.screenPane = new ScrollPane();
        name = screenName;
    }

    public void updateUI() { /*This command is empty because it is overriden by the child classes.*/ }

    public void openRecipe(Recipe recipe) { /*This command is empty because it is overriden by the child classes.*/ }

    public void runBirthMethods() { /*This command is empty because it is overriden by the child classes.*/ }

    public void saveRecipe() { /*This command is empty because it is overriden by the child classes.*/ }

    public void setRecipe(Recipe recipe) { /*This command is empty because it is overriden by the child classes.*/ }

    public Recipe getRecipe()
    {
        /*This command is empty because it is overriden by the child classes.*/
        return new Recipe("[Blank Recipe]");
    }

    public ScrollPane getUI()
    {
        return screenPane;
    }

    protected void setUI(BorderPane inputPane) { screenPane.setContent(inputPane); }

    public String getName()
    {
        return name;
    }

    public String getTitle()
    {
        return "IA " + this.getName() + " Screen";
    }

    public String toString()
    {
        return name;
    }
}
