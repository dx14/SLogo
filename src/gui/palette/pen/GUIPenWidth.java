package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.TextInputDialog;


/**
 * The Class GUIPenWidth extends GUIPenDisplay and controls and displays turtle pen width information.
 */
public class GUIPenWidth extends GUIPenDisplay {
    private String displayInfo;

    /**
     * Instantiates a new GUI pen width display.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPenWidth (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        setTextResources(ResourceBundle.getBundle("resources.guitext.PenWidth"));
        displayInfo = getTextResources().getString("penwidth");
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#redraw()
     */
    @Override
    protected void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add(displayInfo + getTurtles().get(0).getPen().getWidth());
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#setVariable()
     */
    @Override
    protected void setVariable () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText(getTextResources().getString("prompt"));
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                double width = Double.parseDouble(result.get());
                getTurtles().get(0).getPen().setWidth(width);
                redraw();
            }
            catch (Exception e) {
                handleException(e);
            }
        }
    }
}
