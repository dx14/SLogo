package gui.palette.pen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import gui.modelinterface.GUITurtle;
import gui.palette.GUIPaletteTurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import parser.structure.TurtleContainer;


/**
 * The Class GUIPenDisplayContainer extends GUIPaletteTurtle and instantiates and collects the pen-related GUI elements.
 * @author John
 */
public class GUIPenDisplayContainer extends GUIPaletteTurtle {
    private List<GUIPenDisplay> displays;

    /**
     * Instantiates a new GUI pen display container.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPenDisplayContainer (List<GUITurtle> turtles,
                                   GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        displays = new ArrayList<GUIPenDisplay>();
        displays.add(new GUIPenDown(turtles, guiTurtleArea));
        displays.add(new GUIPenWidth(turtles, guiTurtleArea));
        displays.add(new GUIPenStyle(turtles, guiTurtleArea));
    }

    /* (non-Javadoc)
     * @see gui.palette.GUIPaletteTurtle#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        VBox out = new VBox(super.returnNodeToDraw());
        for (GUIPenDisplay d : displays) {
            out.getChildren().add(d.returnNodeToDraw());
        }
        return out;
    }

    /* (non-Javadoc)
     * @see gui.palette.GUIPaletteTurtle#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            for (GUIPenDisplay d : displays) {
                d.update(o, arg);
            }
        }
    }
}
