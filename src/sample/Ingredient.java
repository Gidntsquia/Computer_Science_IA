package sample;

public class Ingredient {
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

    public String getUnits() {
        return units;
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
        // Compare different variables and return a value from 0 to 1.
        return 0;
    }

    public String toString()
    {
        return quantity + " " + units + " of " + name;
    }
}
