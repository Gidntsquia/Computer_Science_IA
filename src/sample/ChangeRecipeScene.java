package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeRecipeScene extends Screen{

    public ChangeRecipeScene(String screenName) {
        super(screenName);
    }

    @Override
    public void createCenter()
    {
        //System.out.println("Overridden3");

        VBox center = new VBox();

        Label centerLabel = new Label("Change the recipe here!!");

        center.getChildren().add(centerLabel);

        screenPane.setCenter(center);
    }


}
