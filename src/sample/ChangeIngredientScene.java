package sample;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;

public class ChangeIngredientScene extends Screen{
    private Ingredient currentIngredient;
    private TextField ingredientNameField;
    private TextField flavorField;
    private TextField unitsField;
    private CheckBox isVegetableBox;
    private CheckBox isFruitBox;
    private CheckBox isMeatBox;
    private CheckBox isSavoryBox;
    private CheckBox isSweetBox;
    private CheckBox isVeganBox;
    private CheckBox isLactoseFreeBox;
    private CheckBox isGlutenFreeBox;

    public ChangeIngredientScene(String screenName) {
        super(screenName);
        flavorField = new TextField();
        unitsField = new TextField();
        isVegetableBox = new CheckBox();
        isFruitBox = new CheckBox();
        isMeatBox = new CheckBox();
        isSavoryBox = new CheckBox();
        isSweetBox = new CheckBox();
        isVeganBox = new CheckBox();
        isLactoseFreeBox = new CheckBox();
        isGlutenFreeBox = new CheckBox();

    }


    public void openChangeIngredientUI()
    {

        // This scene has a unique top UI.
        if(currentIngredient.getName().equals(""))
        {
            Label nameLabel = new Label("Name");
            ingredientNameField = new TextField(currentIngredient.getName());
            changeRecipeTopNodes = new ArrayList<>(Arrays.asList(nameLabel, ingredientNameField));
            refreshGeneralUI();
        }
        else
        {
            Label nameLabel = new Label("Changing:");
            ingredientNameField = new TextField(currentIngredient.getName());
            changeRecipeTopNodes = new ArrayList<>(Arrays.asList(nameLabel, ingredientNameField));
            refreshGeneralUI();
        }


        BorderPane changeIngredientUI = new BorderPane();
        VBox leftUI = new VBox();
        leftUI.getChildren().add(getUILine("Flavor:", flavorField));
        leftUI.getChildren().add(getUILine("Units:", unitsField));
        leftUI.getChildren().add(getUILine("Vegetable?", isVegetableBox));
        leftUI.getChildren().add(getUILine("Fruit?", isFruitBox));
        leftUI.getChildren().add(getUILine("Meat?", isMeatBox));
        leftUI.getChildren().add(getUILine("Savory?", isSavoryBox));
        leftUI.getChildren().add(getUILine("Sweet?", isSweetBox));
        leftUI.getChildren().add(getUILine("Vegan?", isVeganBox));
        leftUI.getChildren().add(getUILine("Is Lactose Free?", isLactoseFreeBox));
        leftUI.getChildren().add(getUILine("Is Gluten Free?", isGlutenFreeBox));


        changeIngredientUI.setLeft(leftUI);
        leftUI.setSpacing(50);

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
    public void saveInfo()
    {
        currentIngredient = new Ingredient(ingredientNameField.getText(), unitsField.getText(), flavorField.getText());
        currentIngredient.setBooleans(isVegetableBox.isSelected(), isFruitBox.isSelected(), isMeatBox.isSelected(), isSavoryBox.isSelected(), isSweetBox.isSelected(), isVeganBox.isSelected(), isLactoseFreeBox.isSelected(), isGlutenFreeBox.isSelected());

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
