package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeScene extends Screen{
    public HomeScene(String screenName) {
        super(screenName);
    }

    @Override
    public void createCenter()
    {
        //System.out.println("Overridden");

        // Accesses screenpane from Screen class
        Recipe sampleRecipe = new Recipe("[Recipe Name]");
        Label sampleRecipeLabel = new Label(sampleRecipe.toString());

        screenPane.setCenter(sampleRecipeLabel);
    }



}
