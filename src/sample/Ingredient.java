package sample;

import org.apache.commons.text.similarity.FuzzyScore;

import java.util.ArrayList;
import java.util.Locale;

public class Ingredient {
    public static ArrayList<Ingredient> allIngredients = new ArrayList<>();
    private String name;
    private double quantity;
    private double defaultQuantity;
    private String units;
    private String flavor;
    private boolean isVegetable;
    private boolean isFruit;
    private boolean isMeat;
    private boolean isSavory;
    private boolean isSweet;
    private boolean isVegan;
    private boolean isLactoseFree;


    public Ingredient(String ingredientName)
    {
        name = ingredientName;
        defaultQuantity = 0;
        quantity = defaultQuantity;
        units = "cups";



    }

    public Ingredient(String ingredientName, double ingredientDefaultQuanitity, String ingredientUnits, String ingredientFlavor)
    {
        name = ingredientName;
        defaultQuantity = ingredientDefaultQuanitity;
        quantity = defaultQuantity;
        units = ingredientUnits;
        flavor = ingredientFlavor;



    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getDefaultQuantity() {
        return defaultQuantity;
    }

    public String getUnits() {
        return units;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setName(String ingredientName) {
        name = ingredientName;
    }

    public void setQuantity(double ingredientQuantity, String ingredientUnits) {
        quantity = ingredientQuantity;
        units = ingredientUnits;
    }

    public void multiplyQuantity(double multiplier)
    {
        quantity = defaultQuantity * multiplier;
    }

    public void resetQuantity()
    {
        quantity = defaultQuantity;
    }

    public int compareTo(Ingredient other)
    {
        if(this.getName() == null || this.getName().equals(""))
        {
            return 100000;
        }
        FuzzyScore fuzzySearch = new FuzzyScore(Locale.US);
        return fuzzySearch.fuzzyScore(this.getName(), other.getName());
    }

    public static void addIngredientToList(Ingredient ingredientToBeAdded)
    {
        allIngredients.add(ingredientToBeAdded);
    }

    public static Ingredient copyIngredient(Ingredient other)
    {
        Ingredient copy = new Ingredient(other.getName(), other.getDefaultQuantity(), other.getUnits(), other.getFlavor());
        return copy;
    }


    public String toString()
    {
        return quantity + " " + units + " of " + name;
    }
}
