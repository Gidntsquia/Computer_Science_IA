package sample;

import java.util.ArrayList;

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

        allIngredients.add(this);



    }

    public Ingredient(String ingredientName, double ingredientDefaultQuanitity, String ingredientUnits, String ingredientFlavor)
    {
        name = ingredientName;
        defaultQuantity = ingredientDefaultQuanitity;
        quantity = defaultQuantity;
        units = ingredientUnits;
        flavor = ingredientFlavor;

        allIngredients.add(this);



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

    public double compare(Ingredient other)
    {
        // TODO- add Pseudocode, expand this compare to include things one letter off
        // Compare different variables and return a value from 0 to 1.
        if(this.getName().equals(other.getName()))
        {
            return 1;
        }
        else if(this.getFlavor().equals(other.getFlavor()))
        {
            return 0.5;
        }
        else
        {
            return 0;
        }
    }

    public static Ingredient copyIngredient(Ingredient other)
    {
        Ingredient copy = new Ingredient(other.getName(), other.getDefaultQuantity(), other.getUnits(), other.getFlavor());
        allIngredients.remove(allIngredients.size() - 1);
        return copy;
    }

    public String toString()
    {
        return quantity + " " + units + " of " + name;
    }
}
