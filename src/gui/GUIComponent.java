package gui;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 * The abstract Class GUIComponent holds common fields and methods that most GUI components should need and extend.
 * @author John, Efe
 */
public abstract class GUIComponent {
    private ResourceBundle textResources;

    /**
     * Return node to draw that will be displayed in the GUI.
     *
     * @return the node
     */
    public abstract Node returnNodeToDraw ();

    /**
     * Handle exception.
     *
     * @param e the e
     */
    protected void handleException (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alert.showAndWait().filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.out.print(""));
    }

    /**
     * Gets the text resources.
     *
     * @return the text resources
     */
    public ResourceBundle getTextResources () {
        return textResources;
    }

    /**
     * Sets the text resources.
     *
     * @param textResources the new text resources
     */
    public final void setTextResources (ResourceBundle textResources) {
        this.textResources = textResources;
    }
}
