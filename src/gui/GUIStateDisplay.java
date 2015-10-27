package gui;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;


public abstract class GUIStateDisplay extends GUIComponent {
    private ScrollPane pane;
    private HBox hbox;

    public GUIStateDisplay () {
        hbox = new HBox();
        pane = new ScrollPane(getHbox());
        setTextResources(ResourceBundle.getBundle("resources.guitext.StateDisplay"));
        pane.setPrefWidth(Integer.parseInt(getTextResources().getString("width")));
    }

    public abstract void redraw ();

    @Override
    public Node returnNodeToDraw () {
        redraw();
        return pane;
    }

    public HBox getHbox () {
        return hbox;
    }
}
