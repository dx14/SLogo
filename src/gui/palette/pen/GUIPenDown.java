package gui.palette.pen;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


public class GUIPenDown extends GUIPenDisplay {
    private String up;
    private String down;
    private String displayInfo;

    public GUIPenDown (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        setTextResources(ResourceBundle.getBundle("resources.guitext.PenDown"));
        up = getTextResources().getString("penup");
        down = getTextResources().getString("pendown");
        displayInfo = getTextResources().getString("displayinfo");
    }

    @Override
    void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add(displayInfo + getTurtles().get(0).getPen().isDown());
    }

    @Override
    void setVariable () {
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
