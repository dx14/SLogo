package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.TextInputDialog;


public class GUIPenWidth extends GUIPenDisplay {
    private String displayInfo;

    public GUIPenWidth (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        setTextResources(ResourceBundle.getBundle("resources.guitext.PenWidth"));
        displayInfo = getTextResources().getString("penwidth");
    }

    @Override
    protected void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add(displayInfo + getTurtles().get(0).getPen().getWidth());
    }

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
