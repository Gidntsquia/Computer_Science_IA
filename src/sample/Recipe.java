package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> methods;
    private int defaultServed;
    private Image image;
    private int daysSinceLastAccess;


    public Recipe(String recipeName)
    {
        name = recipeName;
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeMethods, int recipeDefaultServed, int recipeDaysSinceLastAccess)
    {
       name = recipeName;
       ingredients = recipeIngredients;
       methods = recipeMethods;
       defaultServed = recipeDefaultServed;
       daysSinceLastAccess = recipeDaysSinceLastAccess;
    }

    public Recipe(String recipeName, ArrayList<Ingredient> recipeIngredients, ArrayList<String> recipeMethods, int recipeDefaultServed, int recipeDaysSinceLastAccess, Image recipeImage)
    {
        name = recipeName;
        ingredients = recipeIngredients;
        methods = recipeMethods;
        defaultServed = recipeDefaultServed;
        daysSinceLastAccess = recipeDaysSinceLastAccess;
        image = recipeImage;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getIngredientList()
    {
        String list = "";
        for(int i = 0; i < ingredients.size(); i++)
        {
            list += (i + 1) + ". " + ingredients.get(i) + "\n";
        }
        return list;
    }

    public ArrayList<String> getMethods() {
        return methods;
    }

    public String getMethodList(){
        String list = "";
        for(int i = 0; i < methods.size(); i++)
        {
            list += (i + 1) + ". " + methods.get(i) + "\n";
        }
        return list;
    }
    public int getDefaultServed() {
        return defaultServed;
    }

    public String toString()
    {
        String recipeOverview = "";
        recipeOverview += name + "\n";
        recipeOverview += "Ingredients: \n";
        //recipeOverview += getIngredientList();
        recipeOverview += "Procedure: \n";
        //recipeOverview += getMethodList();

        return recipeOverview;

    }
}
