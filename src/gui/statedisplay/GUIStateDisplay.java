package gui.statedisplay;

import java.util.ResourceBundle;
import gui.GUIComponent;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;


/**
 * The abstract Class GUIStateDisplay extends GUIComponent and lays the groundwork for displaying a horizontal,
 * scrolling list of state variables.
 * @author John
 */
public abstract class GUIStateDisplay extends GUIComponent {
    private ScrollPane pane;
    private HBox hbox;

    /**
     * Instantiates a new GUI state display.
     */
    protected GUIStateDisplay () {
        hbox = new HBox();
        pane = new ScrollPane(getHbox());
        setTextResources(ResourceBundle.getBundle("resources.guitext.StateDisplay"));
        pane.setPrefWidth(Integer.parseInt(getTextResources().getString("width")));
    }

    /**
     * Redraws the component, used for updating the display.
     */
    public abstract void redraw ();

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        redraw();
        return pane;
    }

    /**
     * Gets the hbox of data.
     *
     * @return the hbox
     */
    public HBox getHbox () {
        return hbox;
    }
}
