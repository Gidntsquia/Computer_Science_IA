package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeRecipeScene extends Screen{

    public ChangeRecipeScene(String screenName) {
        super(screenName);
        BorderPane changeRecipeScene = new BorderPane();
        VBox center = new VBox();
        Label centerLabel = new Label("Change the recipe here!!");
        center.getChildren().add(centerLabel);
        changeRecipeScene.setCenter(center);
        setUI(changeRecipeScene);
    }




}
