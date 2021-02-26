package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> procedures;
    private int defaultServed;
    private Image image;
    private int daysSinceLastAccess;


    public Recipe(String recipeName)
    {
        name = recipeName;
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeProcedures, int recipeDefaultServed, int recipeDaysSinceLastAccess)
    {
       name = recipeName;
       ingredients = recipeIngredients;
       procedures = recipeProcedures;
       defaultServed = recipeDefaultServed;
       daysSinceLastAccess = recipeDaysSinceLastAccess;
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeProcedures, int recipeDefaultServed, int recipeDaysSinceLastAccess, Image recipeImage)
    {
        name = recipeName;
        ingredients = recipeIngredients;
        procedures = recipeProcedures;
        defaultServed = recipeDefaultServed;
        daysSinceLastAccess = recipeDaysSinceLastAccess;
        image = recipeImage;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getIngredientList()
    {
        String list = "";
        for(int i = 0; i < ingredients.size(); i++)
        {
            // Numbered list [1. , 2., 3. ...] with a plural version of the ingredient name.
            list += (i + 1) + ". " + ingredients.get(i) + "s\n";
        }
        return list;
    }

    public ArrayList<String> getProcedures() {
        return procedures;
    }

    public String getProcedureList(){
        String list = "";
        for(int i = 0; i < procedures.size(); i++)
        {
            list += (i + 1) + ". " + procedures.get(i) + "\n";
        }
        return list;
    }
    public int getDefaultServed() {
        return defaultServed;
    }


    public GridPane getRecipeBoxUI()
    {
        //TODO add black border to this.
        GridPane recipeBoxUI = new GridPane();
        recipeBoxUI.setHgap(5);

        Button viewRecipeBtn = new Button(this.toString());
        // TODO this should increase in size as the window increases in size.
        viewRecipeBtn.setPrefSize(200, 100);

        // TODO probably a better way to do this.
        viewRecipeBtn.setOnAction(event -> {
            UIManager.openRecipeOverview(this);
            UIManager.showScene(1);
        });


        recipeBoxUI.add(viewRecipeBtn, 0, 0);

        VBox rightBoxUI = new VBox();
        // TODO make this red correctly.
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: pink;");

        rightBoxUI.getChildren().add(deleteBtn);
        rightBoxUI.getChildren().add(new Label("People served: " + this.getDefaultServed()));

        recipeBoxUI.add(rightBoxUI, 1, 0);

        return recipeBoxUI;

    }

    public String toString()
    {
        String recipeOverview = "";
        recipeOverview += name + "\n";
        recipeOverview += "Ingredients: \n";
        if(this.ingredients != null)
        {
            recipeOverview += getIngredientList();
        }

        return recipeOverview;

    }
}
