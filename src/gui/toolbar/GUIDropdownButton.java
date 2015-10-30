package gui.toolbar;

import gui.GUIComponent;
import gui.modelinterface.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


public class GUIDropdownButton extends GUIComponent {
    private ComboBox<Integer> guiDropdown;
    private GUIController myController;

    public GUIDropdownButton (GUIController controller) {
        guiDropdown = new ComboBox<Integer>();
        myController = controller;
        for (int i = 0; i < myController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }
        guiDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                myController.changeGUI(guiDropdown.getValue() - 1); // to
                // align
                // indexing
            }
        });
    }

    public void updateGUINumber () {
        guiDropdown.getItems().clear();
        for (int i = 0; i < myController.getNumberOfGUIs(); i++) {
            guiDropdown.getItems().add(i + 1);
        }
    }

    @Override
    public Node returnNodeToDraw () {
        return guiDropdown;
    }
}
