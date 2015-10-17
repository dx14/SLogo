package gui;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public abstract class GUIComponent {
    private ResourceBundle textResources;

    public abstract Node returnNodeToDraw ();

    protected void handleException (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alert.showAndWait().filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.out.println("handled"));
    }

    public ResourceBundle getTextResources () {
        return textResources;
    }

    public void setTextResources (ResourceBundle textResources) {
        this.textResources = textResources;
    }
}
