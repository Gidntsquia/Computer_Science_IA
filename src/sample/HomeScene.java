package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class HomeScene extends Screen{

    private BorderPane homeScene;

    public HomeScene(String screenName) {
        super(screenName);
        homeScene = new BorderPane();

        // TODO add download recipes from file feature here
        recipes.clear();

        // Accesses screenpane from Screen class
        showRecipes();

        setUI(homeScene);

    }

    @Override
    public void updateUI()
    {
        showRecipes();
        refreshGeneralUI();
    }
    // Save recipe, download recipes
    // Get list of recipes +  recipe maker, make it where you can save recipes

    // This code will: Use a for loop to iterate through the recipes Array List.
    // It will add each Recipe.toString to a String. A label will be initialized
    // with this String and added to the center of the homescene. Then, we update
    // the screenpane to be exactly like the homescene.
    public void showRecipes()
    {
        VBox recipeList = new VBox();
        recipeList.setSpacing(20);
        for(Recipe recipe : recipes)
        {
            recipeList.getChildren().add(recipe.getRecipeBoxUI(
                    event -> {deleteRecipe(recipe);},
                    event -> {openRecipeOverview(recipe);}));
        }
        homeScene.setCenter(recipeList);
    }






}
