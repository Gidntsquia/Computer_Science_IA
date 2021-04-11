package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.apache.commons.text.similarity.FuzzyScore;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class Recipe implements Serializable {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> procedures;
    private int defaultServed;
    private int currentlySurves;
    private Image image;
    private LocalDateTime lastAccessTime;


    public Recipe(String recipeName)
    {
        name = recipeName;
        ingredients = new ArrayList<>(0);
        procedures = new ArrayList<>(0);
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeProcedures, int recipeDefaultServed)
    {
       name = recipeName;
       ingredients = recipeIngredients;
       procedures = recipeProcedures;
       defaultServed = recipeDefaultServed;
       currentlySurves = defaultServed;
       lastAccessTime = LocalDateTime.now();
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeProcedures, int recipeDefaultServed, Image recipeImage)
    {
        name = recipeName;
        ingredients = recipeIngredients;
        procedures = recipeProcedures;
        defaultServed = recipeDefaultServed;
        currentlySurves = defaultServed;
        lastAccessTime = LocalDateTime.now();
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
                list += (i + 1) + ". " + ingredients.get(i) + "\n";
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

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public int getDefaultServed() {
        return defaultServed;
    }

    public int getCurrentlySurves() {
        return currentlySurves;
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
                summaryText += ingredients.get(0).getName().toLowerCase() + ", ";
            }
            else if(ingredients.size() == 2)
            {
                summaryText += ingredients.get(0).getName().toLowerCase() + " ";
                summaryText += "and " + ingredients.get(1).getName().toLowerCase() + ", ";
            }
            else
            {
                for(int i = 0; i < ingredients.size(); i++)
                {
                    if(i == ingredients.size() - 1)
                    {
                        summaryText += "and " + ingredients.get(i).getName().toLowerCase() + ", ";
                    }
                    else
                    {
                        summaryText += ingredients.get(i).getName().toLowerCase() + ", ";
                    }
                }
            }
        }

        if(procedures != null)
        {
            // Iterates through all procedures, adding each procedure's name to the string representation of procedures.
            // Hard coded functionality for grammar if there are only 0, 1, or 2 procedures.
            if(procedures.size() == 0)
            {
                summaryText = summaryText.replace("Using ", "Made with ");
                summaryText = summaryText.substring(0, summaryText.length() - 2) + ".";
            }
            else if(procedures.size() == 1)
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
        viewRecipeBtn.setPrefSize(400, 150);

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

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public void setDefaultServed(int defaultServed) {
        this.defaultServed = defaultServed;
        this.currentlySurves = defaultServed;
    }

    public void setCurrentlySurves(int currentlySurves) {
        this.currentlySurves = currentlySurves;
    }

    public void resetCurrentlyServes()
    {
        this.currentlySurves = defaultServed;
        for(int i = 0; i < this.getIngredients().size(); i++)
        {
            this.getIngredients().get(i).resetQuantity();
        }
    }

    // TODO implement this
    public void setImage(Image image) {
        this.image = image;
    }

    // Used to sort the recipes ArrayList. Gauges how much closer one recipe is than another to
    // searchText. Recipes closer to the search text get lower FuzzyScores. Recipe names are
    // given a higher weight than ingredients to give more intuitive results.
    public int compareTo(Recipe other, String searchText)
    {
        // Recipes without a name are put at the bottom of the sort since List is reversed later.
        if(this.getName() == null || this.getName().equals(""))
        {
            return 100000;
        }
        else if(other.getName() == null || other.getName().equals(""))
        {
            return -100000;
        }
        FuzzyScore fuzzySearch = new FuzzyScore(Locale.US);
        // Search terms checked against each ingredient, and overall closeness divided by total number
        // of ingredients to get average closeness. Very close matches are given particular weight.
        int closeness;
        int ingredientsCloseness1 = 0;
        if(this.getIngredients().size() == 0)
        {
            // This ensures no division by 0.
            ingredientsCloseness1 = 50;
        }
        else
        {
            for(Ingredient ingredient : this.getIngredients())
            {
                closeness = fuzzySearch.fuzzyScore(ingredient.getName(), searchText);
                if(closeness > 8)
                {
                    ingredientsCloseness1 += closeness;
                }
                else
                {
                    ingredientsCloseness1 -= 10;
                }
            }
            ingredientsCloseness1 /= this.getIngredients().size();
        }
        int ingredientsCloseness2 = 0;
        if(other.getIngredients().size() == 0)
        {
            // This ensures no division by 0.
            ingredientsCloseness2 = 50;
        }
        else
        {
            for(Ingredient ingredient : other.getIngredients())
            {
                closeness = fuzzySearch.fuzzyScore(ingredient.getName(), searchText);
                if(closeness > 8)
                {
                    ingredientsCloseness2 += closeness;
                }
                else
                {
                    ingredientsCloseness2 -= 10;
                }
            }
            ingredientsCloseness2 /= other.getIngredients().size();
        }

        return (int)(fuzzySearch.fuzzyScore(other.getName(), searchText) * 2  + ingredientsCloseness2)
                - (int)(fuzzySearch.fuzzyScore(this.getName(), searchText) * 2 + ingredientsCloseness1);
    }

    public void updateLastAccessToNow()
    {
        lastAccessTime = LocalDateTime.now();
    }

    @Override
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
