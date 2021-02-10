package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeScene extends Screen{
    public HomeScene(String screenName) {
        super(screenName);
        BorderPane homeScene = new BorderPane();
        // Accesses screenpane from Screen class
        Recipe sampleRecipe = new Recipe("[Recipe Name]");
        Label sampleRecipeLabel = new Label(sampleRecipe.toString());

        homeScene.setCenter(sampleRecipeLabel);

        screenPane = homeScene;

    }



}
