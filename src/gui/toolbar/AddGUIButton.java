package gui.toolbar;

import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * The Class AddGUIButton extends GUIComponent and adds a new GUI workspace instance.
 * @author Efe
 */
public class AddGUIButton extends GUIComponent {
    private Button add;

    /**
     * Instantiates a new add gui button.
     *
     * @param controller the controller
     */
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

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        return add;
    }
}
