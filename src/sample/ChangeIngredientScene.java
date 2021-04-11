package sample;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChangeIngredientScene extends Screen{
    private Ingredient currentIngredient;
    private TextField ingredientNameField;
    private TextField flavorField;
    private TextField unitsField;

    public ChangeIngredientScene(String screenName) {
        super(screenName);

        flavorField = new TextField();
        unitsField = new TextField();
    }


    public void openChangeIngredientUI()
    {

        // This scene has a unique top UI.
        // Ingredient name will be "" if it is a new ingredient.
        if(currentIngredient.getName().equals(""))
        {
            // Code for new ingredient
            Label nameLabel = new Label("Name");
            ingredientNameField = new TextField(currentIngredient.getName());
            changeRecipeTopNodes = new ArrayList<>(Arrays.asList(nameLabel, ingredientNameField));
            refreshGeneralUI();
        }
        else
        {
            // Code for changing existing ingredient
            Label nameLabel = new Label("Changing:");
            ingredientNameField = new TextField(currentIngredient.getName());
            changeRecipeTopNodes = new ArrayList<>(Arrays.asList(nameLabel, ingredientNameField));
            refreshGeneralUI();
        }

        BorderPane changeIngredientUI = new BorderPane();
        VBox leftUI = new VBox();
        flavorField.setText(currentIngredient.getFlavor());
        unitsField.setText(currentIngredient.getUnits());

        leftUI.getChildren().add(getUILine("Units:", unitsField));

        changeIngredientUI.setLeft(leftUI);
        leftUI.setSpacing(25);

        setUI(changeIngredientUI);

    }

    public HBox getUILine(String dataName, Node dataNode)
    {
        HBox UILine = new HBox();
        Label dataLabel = new Label(dataName);
        UILine.getChildren().addAll(dataLabel, dataNode);

        return UILine;
    }

    @Override
    public void runBirthMethods()
    {
        refreshGeneralUI();
        openChangeIngredientUI();
    }

    @Override
    public String getTitle()
    {
        if(UIManager.creatingNewIngredient)
        {
            return "Creating New Ingredient";
        }
        else
        {
            return this.getName() + " Screen. Changing: " + currentIngredient.getName();
        }

    }

    @Override
    public void saveInfo()
    {
        // Adds ingredient if it is a new ingredient. Otherwise, it replaces the original one.
        if(UIManager.creatingNewIngredient)
        {
            currentIngredient = new Ingredient(ingredientNameField.getText(), unitsField.getText());
            Ingredient.allIngredients.add(currentIngredient);
        }
        else
        {
            Ingredient.allIngredients.set(Ingredient.allIngredients.indexOf(currentIngredient),
                    new Ingredient(ingredientNameField.getText(), unitsField.getText()));
        }
    }

    @Override
    public Ingredient getIngredient()
    {
        return currentIngredient;
    }

    @Override
    public void setIngredient(Ingredient ingredient)
    {
        currentIngredient = ingredient;
    }
}
