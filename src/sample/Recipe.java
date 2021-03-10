package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;

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
        ingredients = new ArrayList<>(0);
        procedures = new ArrayList<>(0);
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
        if(ingredients != null)
        {
            for(int i = 0; i < ingredients.size(); i++)
            {
                // Numbered list [1. , 2., 3. ...] with a plural version of the ingredient name.
                list += (i + 1) + ". " + ingredients.get(i) + "s\n";
            }
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

    public int getDaysSinceLastAccess() {
        return daysSinceLastAccess;
    }

    public int getDefaultServed() {
        return defaultServed;
    }

    public String getOverview()
    {
        String summaryText = "Using ";
        if(ingredients != null)
        {
            // Iterates through all ingredients, adding each ingredient's name to the string representation of ingredients.
            // Hard coded functionality for if there are only 1 or 2 ingredients.
            if(ingredients.size() == 1)
            {
                summaryText += ingredients.get(0).getName().toLowerCase() + "s, ";
            }
            else if(ingredients.size() == 2)
            {
                summaryText += ingredients.get(0).getName().toLowerCase() + "s ";
                summaryText += "and " + ingredients.get(1).getName().toLowerCase() + "s, ";
            }
            else
            {
                for(int i = 0; i < ingredients.size(); i++)
                {
                    if(i == ingredients.size() - 1)
                    {
                        summaryText += "and " + ingredients.get(i).getName().toLowerCase() + "s, ";
                    }
                    else
                    {
                        summaryText += ingredients.get(i).getName().toLowerCase() + "s, ";
                    }


                }
            }
        }

        if(procedures != null)
        {
            // Iterates through all procedures, adding each procedure's name to the string representation of procedures.
            // Hard coded functionality for if there are only 1 or 2 procedures.
            if(procedures.size() == 1)
            {
                summaryText += procedures.get(0).toLowerCase() + ". ";
            }
            else if(procedures.size() == 2)
            {
                summaryText += procedures.get(0).toLowerCase() + " ";
                summaryText += "and then " + procedures.get(1).toLowerCase() + ".";
            }
            else
            {
                for(int i = 0; i < procedures.size(); i++)
                {
                    if(i == procedures.size() - 1)
                    {
                        summaryText += "and then " + procedures.get(i).toLowerCase() + ".";
                    }
                    else
                    {
                        summaryText += procedures.get(i).toLowerCase() + ", ";
                    }
                }
            }
        }


        return summaryText;
    }


    public GridPane getRecipeBoxUI(EventHandler deleteEvent, EventHandler viewEvent)
    {
        //TODO add black border to this.
        GridPane recipeBoxUI = new GridPane();
        recipeBoxUI.setHgap(5);

        Button viewRecipeBtn = new Button(this.toString());
        // TODO this should increase in size as the window increases in size.
        viewRecipeBtn.setPrefSize(200, 100);

        viewRecipeBtn.setOnAction(viewEvent);


        recipeBoxUI.add(viewRecipeBtn, 0, 0);

        VBox rightBoxUI = new VBox();
        // TODO make this red correctly.
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: pink;");
        deleteBtn.setOnAction(deleteEvent);

        rightBoxUI.getChildren().add(deleteBtn);
        rightBoxUI.getChildren().add(new Label("People served: " + this.getDefaultServed()));

        recipeBoxUI.add(rightBoxUI, 1, 0);

        return recipeBoxUI;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setProcedures(ArrayList<String> procedures) {
        this.procedures = procedures;
    }

    public void setDaysSinceLastAccess(int daysSinceLastAccess) {
        this.daysSinceLastAccess = daysSinceLastAccess;
    }

    public void setDefaultServed(int defaultServed) {
        this.defaultServed = defaultServed;
    }

    // TODO implement this
    public void setImage(Image image) {
        this.image = image;
    }

    public int compareTo(Recipe other, String searchText)
    {
        if(this.getName() == null || this.getName().equals(""))
        {
            return 100000;
        }
        return (int)((Math.abs(searchText.compareTo(this.getName()) * 5)) + this.getDaysSinceLastAccess()) * 2 - (int)(Math.abs(searchText.compareTo(other.getName()) * 5) + other.getDaysSinceLastAccess() * 2);
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
