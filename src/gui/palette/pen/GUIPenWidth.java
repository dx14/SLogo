package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.TextInputDialog;


public class GUIPenWidth extends GUIPenDisplay {
    public GUIPenWidth (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
    }

    @Override
    void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add("Pen Width: " + getTurtles().get(0).getPen().getWidth());
    }

    @Override
    void setVariable () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Enter new pen width:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                double width = Double.parseDouble(result.get());
                getTurtles().get(0).getPen().setWidth(width);
                redraw();// not the best, need to refresh manually right now since updating the pen
                         // doesn't call the observer
            }
            catch (Exception e) {
                handleException(e);
            }
        }
    }
}
