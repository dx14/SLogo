package gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
                    turtle.setPenColor(toColorIndex(penColor));
                }
            }
        });
        return colorPicker;
    }
    
    // TODO: implement this to return actual index
    
    private static int toColorIndex (Color color) {
    	return 0;
    }

    private static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
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
