package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecipeOverviewScene extends Screen{

    public RecipeOverviewScene(String screenName) {
        super(screenName);
    }

    @Override
    public void createCenter()
    {
        //System.out.println("Overridden2");

        VBox center = new VBox();

        // Accesses screenpane from Screen class
        Label overviewLabel = new Label("Overview:");

        Recipe sampleRecipe = new Recipe("[Recipe Name]");
        Label sampleRecipeLabel = new Label(sampleRecipe.toString());

        center.getChildren().addAll(overviewLabel, sampleRecipeLabel);


        screenPane.setCenter(center);
    }
}
