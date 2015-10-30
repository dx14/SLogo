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


/**
 * The Class GUIPaletteTurtle extends GUIPalette and represents the color picker for the turtle pen.
 * It implements Observer to reflect changes to the pen color from the back end.
 * The default pen color is set to black instead of the default value loaded from the XML.
 * @author John
 */
public class GUIPaletteTurtle extends GUIPalette implements Observer {

    private List<GUITurtle> myTurtles;

    /**
     * Instantiates a new GUI palette turtle.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPaletteTurtle (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(guiTurtleArea);
        myTurtles = turtles;
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
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

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            myTurtles.set(0, turtle);
            turtle.completeUpdate();
        }
    }

    /**
     * Gets the turtles.
     *
     * @return the turtles
     */
    public List<GUITurtle> getTurtles () {
        return myTurtles;
    }
}
