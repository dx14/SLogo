package gui;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class GUIPaletteTurtle extends GUIPalette {

    private List<GUITurtle> myTurtles;
    private GUITurtleAreaBGInterface myGuiTurtleArea;

    public GUIPaletteTurtle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        myTurtles = turtles;
        myGuiTurtleArea = guiTurtleArea;
    }

    @Override
    public Node returnNodeToDraw () {
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t) {
                Color penColor = (colorPicker.getValue());
                for (GUITurtle turtle : myTurtles) {
                    //TODO: which turtle to set color? Right now it sets all turtles. Also does it change the color or already drawn paths?
                    turtle.setPenColor(penColor);
                }
            }
        });
        return colorPicker;
    }

}
