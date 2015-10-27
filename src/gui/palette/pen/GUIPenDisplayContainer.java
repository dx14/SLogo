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


public class GUIPenDisplayContainer extends GUIPaletteTurtle {
    private List<GUIPenDisplay> displays;

    public GUIPenDisplayContainer (List<GUITurtle> turtles,
                                   GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
        displays = new ArrayList<GUIPenDisplay>();
        displays.add(new GUIPenDown(turtles, guiTurtleArea));
        displays.add(new GUIPenWidth(turtles, guiTurtleArea));
        displays.add(new GUIPenStyle(turtles, guiTurtleArea));
    }

    @Override
    public Node returnNodeToDraw () {
        VBox out = new VBox(super.returnNodeToDraw());
        for (GUIPenDisplay d : displays) {
            out.getChildren().add(d.returnNodeToDraw());
        }
        return out;
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("work3");
        if (o instanceof TurtleContainer) {
            for (GUIPenDisplay d : displays) {
                d.update(o, arg);
            }
        }
        else {
            // make this an exception
            System.out
                    .println("update didnt update it; it might not be an instance of GUITurtleContainer");
        }
    }
}
