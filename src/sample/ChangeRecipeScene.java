package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.util.ArrayList;

public class ChangeRecipeScene extends Screen{
    Recipe currentRecipe;

    public ChangeRecipeScene(String screenName) {
        super(screenName);
        BorderPane changeRecipeScene = new BorderPane();
        VBox center = new VBox();
        Label centerLabel = new Label("Change the recipe here!!");
        currentRecipe = new Recipe("[Blank Recipe]");

        center.getChildren().add(centerLabel);
        changeRecipeScene.setCenter(center);
        setUI(changeRecipeScene);
    }

    // This code will create textfields for each part of the recipe.
    // It will then fill each textfield with the already existing values for each part of the recipe
    // i.e. the method list will have all the existing methods already put into the textfields
    // The user can change whatever they'd like about the recipe, and then when they hit "Save",
    // the recipe will update its values to reflect the user's changes.
    // There will be a dropdown menu to add/remove ingredients
    public void changeRecipe(Recipe recipe, Button saveBtn)
    {
        currentRecipe = recipe;
        TextField recipeNameField = new TextField(recipe.getName());
        ArrayList<TextField> ingredientFields = new ArrayList<>();
        ArrayList<TextField> procedureFields = new ArrayList<>();
        TextField defaultServedField = new TextField(String.valueOf(recipe.getDefaultServed()));
        saveBtn.setOnAction(event -> {


        });
    }

    // This will work the same as change recipe, except that it will populate the textfields with nothing.
    public void createRecipe()
    {

    }




}
