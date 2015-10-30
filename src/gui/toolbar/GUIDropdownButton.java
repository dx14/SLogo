package gui.toolbar;

import gui.GUIComponent;
import gui.modelinterface.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


/**
 * The Class GUIDropdownButton extends GUIComponent and represents the gui workspace instance selector
 * @author Efe
 */
public class GUIDropdownButton extends GUIComponent {
    private ComboBox<Integer> guiDropdown;
    private GUIController myController;

    /**
     * Instantiates a new GUI dropdown button.
     *
     * @param controller the controller
     */
    public GUIDropdownButton (GUIController controller) {
        guiDropdown = new ComboBox<Integer>();
        myController = controller;
        updateGUINumber();
        guiDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myController.changeGUI(guiDropdown.getValue() - 1); // to
                // align
                // indexing
            }
        });
    }

    /**
     * Update the gui workspace instance number.
     */
    public void updateGUINumber () {
        guiDropdown.getItems().clear();
        for (int i = 0; i < myController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        return guiDropdown;
    }
}
