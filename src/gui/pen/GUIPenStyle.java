package gui.pen;

import java.util.List;
import java.util.Optional;
import gui.GUITurtle;
import gui.GUITurtleAreaBGInterface;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import util.LineStyle;

public class GUIPenStyle extends GUIPenDisplay{
    public GUIPenStyle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
    }

    @Override
    void redraw () {
        getWhatToShow().clear();
        getWhatToShow().add("Pen Style: " + getTurtles().get(0).getPen().getStyle().toString());
    }
    void setVariable () {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        for (LineStyle s: LineStyle.values()) {
            dialog.getDialogPane().getButtonTypes().add(new ButtonType(s.toString()));
        }

        // Traditional way to get the response value.
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            try{
                getTurtles().get(0).getPen().setStyle(LineStyle.valueOf(result.get().getText()));
                System.out.println(result.get().getText());
            }
            catch (Exception e){
                handleException(e);
            }
        }
        redraw();
    }
}