package gui.statedisplay;

import java.util.Optional;
import gui.turtlearea.GUITADrawerSpeedInterface;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


/**
 * The Class GUIAnimationSpeedDisplay extends GUIStateDisplay and controls the animation duration of the turtle area.
 * @author John
 */
public class GUIAnimationSpeedDisplay extends GUIStateDisplay {
    private GUITADrawerSpeedInterface myDrawer;
    private Text text;

    /**
     * Instantiates a new GUI animation speed display.
     *
     * @param drawer the drawer
     */
    public GUIAnimationSpeedDisplay (GUITADrawerSpeedInterface drawer) {
        myDrawer = drawer;
    }

    /* (non-Javadoc)
     * @see gui.statedisplay.GUIStateDisplay#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        text = new Text(getTextResources().getString("animationdisplay") + myDrawer.getSpeed());
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setContentText(getTextResources().getString("animationprompt"));
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    try {
                        double speed = Double.parseDouble(result.get());
                        myDrawer.setSpeed(speed);
                        redraw();
                    }
                    catch (Exception e) {
                        handleException(e);
                    }
                }
            }
        });
        return text;
    }

    /* (non-Javadoc)
     * @see gui.statedisplay.GUIStateDisplay#redraw()
     */
    @Override
    public void redraw () {
        text.setText("" + myDrawer.getSpeed());
    }
}
