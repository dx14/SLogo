package gui.palette;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import gui.modelinterface.GUITurtle;
import gui.modelinterface.GUITurtleContainer;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import parser.structure.TurtleContainer;


public class GUIPaletteTurtle extends GUIPalette implements Observer {

    private List<GUITurtle> myTurtles;

    public GUIPaletteTurtle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(guiTurtleArea);
        myTurtles = turtles;
    }

    @Override
    public Node returnNodeToDraw () {
        getPicker().setValue(Color.BLACK);
        getPicker().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent t) {
                Color penColor = (getPicker().getValue());
                int i = addToPalette(penColor);
                for (GUITurtle turtle : myTurtles) {
                    turtle.setPenColor(i);
                }
            }
        });
        return getPicker();
    }

    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            myTurtles.set(0, turtle);
            turtle.completeUpdate();
        }
    }

    public List<GUITurtle> getTurtles () {
        return myTurtles;
    }
}
