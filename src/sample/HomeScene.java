package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class HomeScene extends Screen{
    private BorderPane homeScene;

    public HomeScene(String screenName) {
        super(screenName);
        homeScene = new BorderPane();
        showRecipes();
        // Sets UI for this scene with a method from Screen super class.
        setUI(homeScene);
    }

    @Override
    public void updateUI()
    {
        showRecipes();
        refreshGeneralUI();
    }

    // Displays recipes in recipes ArrayList in order. Also
    // adds access and delete functionality.
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
