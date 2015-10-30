package gui.toolbar;

import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class AddGUIButton extends GUIComponent {
    private Button add;

    public AddGUIButton (GUIController controller) {
        setTextResources(ResourceBundle.getBundle("resources.guitext.AddGUI"));
        add = new Button(getTextResources().getString("addGUI"));
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                controller.addGUI();
            }
        });
    }

    @Override
    public Node returnNodeToDraw () {
        return add;
    }
}
