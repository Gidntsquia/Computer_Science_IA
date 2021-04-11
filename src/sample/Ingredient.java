package sample;

import javafx.scene.control.CheckBox;
import org.apache.commons.text.similarity.FuzzyScore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class Ingredient implements Serializable {
    public static ArrayList<Ingredient> allIngredients = new ArrayList<>();
    public static ArrayList<String> allDescriptors = new ArrayList<>(Arrays.asList("Vegetable", "Fruit", "Meat", "Savory", "Sweet", "Vegan", "Lactose Free", "Gluten Free", "Starchy"));
    public HashMap<String, Boolean> descriptors = new HashMap<>();
    private String name;
    private double quantity;
    private double defaultQuantity;
    private String units;
    private String flavor;

    public Ingredient(String ingredientName)
    {
        name = ingredientName;
        defaultQuantity = 0;
        quantity = defaultQuantity;
        units = "";
        initializeBooleans();



    }

    public Ingredient(String ingredientName, String units)
    {
        this.name = ingredientName;
        this.units = units;
        initializeBooleans();
    }

    public Ingredient(String ingredientName, double ingredientDefaultQuanitity, String ingredientUnits, String ingredientFlavor)
    {
        name = ingredientName;
        defaultQuantity = ingredientDefaultQuanitity;
        quantity = defaultQuantity;
        units = ingredientUnits;
        flavor = ingredientFlavor;
        initializeBooleans();



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

    public HashMap<String, Boolean> getDescriptors() {
        return descriptors;
    }

    public void setName(String ingredientName) {
        name = ingredientName;
    }

    public void setQuantity(double ingredientQuantity) {
        quantity = ingredientQuantity;
    }

    public void setDefaultQuantity(double defaultQuantity)
    {
        this.defaultQuantity = defaultQuantity;
        this.quantity = defaultQuantity;
    }

    public void setBooleans(Boolean... booleans)
    {
        for(int i = 0; i < allDescriptors.size(); i++)
        {
            this.descriptors.replace(allDescriptors.get(i), booleans[i]);
        }
    }

    public void setBooleans(HashMap<String, CheckBox> booleans)
    {
        this.descriptors.clear();
        for(String key : booleans.keySet())
        {
            this.descriptors.put(key.substring(0, key.length() - 1), booleans.get(key).isSelected());

        }
    }

    // Converts ArrayList of Strings to HashMap with Strings and corresponding booleans.
    public void initializeBooleans()
    {
        for(String descriptor : allDescriptors)
        {
            //System.out.println(descriptor);
            descriptors.put(descriptor, false);
        }
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
