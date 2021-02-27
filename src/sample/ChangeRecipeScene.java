package sample;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChangeRecipeScene extends Screen{
    private Recipe currentRecipe;
    private TextField recipeNameField;
    private ArrayList<Ingredient> ingredients;
    private VBox ingredientMenusUI;
    private ArrayList<TextField> procedureFields;
    private TextField defaultServedField;

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
    public void openChangeRecipeUI()
    {
        // ChangeRecipeUI = overall UI
        // leftUI = all left UI
        // ingredientMenus = ingredients in left UI
        // procedureFields = procedures in left UI
        BorderPane changeRecipeUI = new BorderPane();
        VBox leftUI = new VBox();
        ingredientMenusUI = new VBox();
        VBox procedureFieldsUI = new VBox();

        HBox rightUI = new HBox();

        recipeNameField = new TextField(currentRecipe.getName());
        ingredients = currentRecipe.getIngredients();
        for(int i = 0; i < ingredients.size(); i++)
        {
            ingredientMenusUI.getChildren().add(getIngredientLine(ingredients.get(i), i));
        }

        procedureFields = new ArrayList<>();
        for(int i = 0; i < currentRecipe.getProcedures().size(); i++)
        {
            procedureFields.add(new TextField(currentRecipe.getProcedures().get(i)));
            procedureFieldsUI.getChildren().add(procedureFields.get(i));
        }
        defaultServedField = new TextField(String.valueOf(currentRecipe.getDefaultServed()));

        Button addIngredientBtn = new Button("+ Add Ingredient");
        addIngredientBtn.setOnAction(event -> {

        });
        Button addProcedureBtn = new Button("+ Add Procedure");
        addProcedureBtn.setOnAction(event -> {
            procedureFields.add(new TextField());
            procedureFieldsUI.getChildren().add(procedureFields.get(procedureFields.size() - 1));
        });

        leftUI.setSpacing(10);

        leftUI.getChildren().addAll(new Label("Ingredients"), ingredientMenusUI, getNewIngredientDropdown(), new Label("Procedures"), procedureFieldsUI, addProcedureBtn);
        rightUI.getChildren().addAll(new Label("Serves Default: "), defaultServedField);

        changeRecipeUI.setLeft(leftUI);
        changeRecipeUI.setRight(rightUI);
        changeRecipeUI.setTop(new Label("Overview\n" + currentRecipe.getOverview()));


        setUI(changeRecipeUI);

    }

    // This will work the same as change recipe, except that it will populate the textfields with nothing.
    public void openCreateRecipeUI()
    {

    }

    public ArrayList<String> getStringsFromTextFields(ArrayList<TextField> fields)
    {
        ArrayList<String> strings = new ArrayList<>();
        if(fields.size() > 0)
        {
            for(TextField field : fields)
            {
                strings.add(field.getText());
            }
        }
        return strings;
    }

    public MenuButton getChangeIngredientDropdown(int lineIndex)
    {
        // Adds all ingredients ever created to a dropdown menu.
        MenuButton ingredientDropdown = new MenuButton(" + Ingredients", null);
        for(int i = 0; i < Ingredient.allIngredients.size(); i++)
        {
            ingredientDropdown.getItems().add(new MenuItem(Ingredient.allIngredients.get(i).getName()));
            int finalI = i;
            ingredientDropdown.getItems().get(i).setOnAction(event -> {
                ingredients.set(lineIndex, Ingredient.copyIngredient(Ingredient.allIngredients.get(finalI)));
                ingredientMenusUI.getChildren().set(lineIndex, getIngredientLine(ingredients.get(lineIndex), lineIndex));
            });
        }
        return ingredientDropdown;
    }

    public MenuButton getNewIngredientDropdown()
    {
        // Adds all ingredients ever created to a dropdown menu
        MenuButton ingredientDropdown = new MenuButton(" + Ingredients", null);
        for(int i = 0; i < Ingredient.allIngredients.size(); i++)
        {
            ingredientDropdown.getItems().add(new MenuItem(Ingredient.allIngredients.get(i).getName()));
            int finalI = i;
            ingredientDropdown.getItems().get(i).setOnAction(event -> {
                ingredients.add(Ingredient.allIngredients.get(finalI));
                ingredientMenusUI.getChildren().add(getIngredientLine(Ingredient.allIngredients.get(finalI), ingredients.size() - 1));
            });
        }
        return ingredientDropdown;
    }

    public HBox getIngredientLine(Ingredient ingredient, int lineIndex)
    {
        HBox ingredientAndSelector = new HBox();
        ingredientAndSelector.getChildren().add(new Label(ingredient.getName()));
        ingredientAndSelector.getChildren().add(getChangeIngredientDropdown(lineIndex));

        return ingredientAndSelector;
    }

    @Override
    public void updateUI()
    {

    }

    @Override
    public void runBirthMethods()
    {
        Ingredient potato = new Ingredient("Potato", 5, "cups", "starchy");
        Ingredient tomato = new Ingredient("Tomato", 2, "cups", "acidic");
        ArrayList<Ingredient> basicIngredients = new ArrayList<>();
        basicIngredients.add(potato);
        basicIngredients.add(tomato);


        String method1 = "Skin the potato";
        String method2 = "Wash the tomato";
        String method3 = "Put it all together";
        ArrayList<String> basicMethods = new ArrayList<>();
        basicMethods.add(method1);
        basicMethods.add(method2);
        basicMethods.add(method3);

        Recipe recipe = new Recipe("Sample Recipe", basicIngredients, basicMethods, 651, 3);

        openChangeRecipeUI();
    }

    @Override
    public void saveRecipe()
    {
        // TODO need to make it where you can change name of Recipe
        currentRecipe.setName(recipeNameField.getText());
        // Ingredients

        currentRecipe.setProcedures(getStringsFromTextFields(procedureFields));
        currentRecipe.setDefaultServed(Integer.parseInt(defaultServedField.getText()));

        // Sets most recent access day to today.
        currentRecipe.setDaysSinceLastAccess(0);
    }

    @Override
    public Recipe getRecipe()
    {
        return this.currentRecipe;
    }

    @Override
    public void setRecipe(Recipe recipe)
    {
        this.currentRecipe = recipe;
    }




}
