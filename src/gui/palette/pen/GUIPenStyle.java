package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import util.LineStyle;


/**
 * The Class GUIPenStyle extends GUIPenDisplay and controls and displays turtle pen style information.
 * @author John
 */
public class GUIPenStyle extends GUIPenDisplay {
    private String displayInfo;

    /**
     * Instantiates a new GUI pen style display.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPenStyle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        setTextResources(ResourceBundle.getBundle("resources.guitext.PenStyle"));
        displayInfo = getTextResources().getString("penstyle");
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#redraw()
     */
    @Override
    protected void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add(displayInfo + getTurtles().get(0).getPenStyle().toString());
    }

    /* (non-Javadoc)
     * @see gui.palette.pen.GUIPenDisplay#setVariable()
     */
    @Override
    protected void setVariable () {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        for (LineStyle s : LineStyle.values()) {
            dialog.getDialogPane().getButtonTypes().add(new ButtonType(s.toString()));
        }

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                getTurtles().get(0).setPenStyle(LineStyle.valueOf(result.get().getText()));
            }
            catch (Exception e) {
                handleException(e);
            }
        }
        redraw();
    }
}
