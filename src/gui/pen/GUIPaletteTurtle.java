package gui.pen;

import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import gui.GUIPalette;
import gui.GUITurtle;
import gui.GUITurtleAreaBGInterface;
import gui.GUITurtleAreaPaletteInterface;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import parser.structure.GUITurtleContainer;
import parser.structure.TurtleContainer;


public class GUIPaletteTurtle extends GUIPalette implements Observer {

    private List<GUITurtle> myTurtles;

    public GUIPaletteTurtle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(guiTurtleArea);
        myTurtles = turtles;
    }

    @Override
    public Node returnNodeToDraw () {
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent t) {
                Color penColor = (colorPicker.getValue());
                int i=addToPalette(penColor);
                for (GUITurtle turtle : myTurtles) {
                    //TODO: which turtle to set color? Right now it sets all turtles. Also does it change the color or already drawn paths?
                    turtle.setPenColor(i);
                }
            }
        });
        return colorPicker;
    }


    @Override
    public void update (Observable o, Object arg) {
        if(o instanceof TurtleContainer){
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            myTurtles.set(0, turtle);
            turtle.completeUpdate();
        }

        else{
            //make this an exception
            System.out.println("update didnt update it; it might not be an instance of GUITurtleContainer");
        }
    }
    public List<GUITurtle> getTurtles() {
        return myTurtles;
    }
}
