package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


/**
 * The Class GUIPenDown extends GUIPenDisplay and controls and displays turtle pen down information.
 * @author John
 */
public class GUIPenDown extends GUIPenDisplay {
    private String up;
    private String down;
    private String displayInfo;

    /**
     * Instantiates a new GUI pen down display.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPenDown (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        setTextResources(ResourceBundle.getBundle("resources.guitext.PenDown"));
        up = getTextResources().getString("penup");
        down = getTextResources().getString("pendown");
        displayInfo = getTextResources().getString("displayinfo");
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#redraw()
     */
    @Override
    protected void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add(displayInfo + getTurtles().get(0).getPen().isDown());
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#setVariable()
     */
    @Override
    protected void setVariable () {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        ButtonType upButtonType = new ButtonType(up);
        dialog.getDialogPane().getButtonTypes().add(upButtonType);
        ButtonType downButtonType = new ButtonType(down);
        dialog.getDialogPane().getButtonTypes().add(downButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                if (result.get().getText().equals(down)) {
                    getTurtles().get(0).penDown();
                }
                else if (result.get().getText().equals(up)) {
                    getTurtles().get(0).penUp();
                }
                redraw();
            }
            catch (Exception e) {
                handleException(e);
            }
        }
    }

}
