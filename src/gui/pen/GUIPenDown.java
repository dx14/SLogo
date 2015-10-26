package gui.pen;

import java.util.List;
import java.util.Optional;
import gui.GUITurtle;
import gui.GUITurtleAreaBGInterface;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;


public class GUIPenDown extends GUIPenDisplay {
    String up = "Pen Up";
    String down = "Pen Down";

    public GUIPenDown (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
    }

    @Override
    void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add("Pen Down: " + getTurtles().get(0).getPen().isDown());
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
