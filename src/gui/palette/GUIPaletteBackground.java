package gui.palette;

import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;


/**
 * The Class GUIPaletteBackground extends GUIPalette. It represents the chooser for the background color of the turtle area.
 * @author John, Efe
 */
public class GUIPaletteBackground extends GUIPalette {

    private Color backgroundColor;

    /**
     * Instantiates a new GUI palette background.
     *
     * @param col the initial background color
     * @param guiTurtleArea the gui turtle area
     */
    public GUIPaletteBackground (Color col, GUITurtleAreaBGInterface guiTurtleArea) {
        super(guiTurtleArea);
        backgroundColor = col;
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        getPicker().setValue(backgroundColor);

        getPicker().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent t) {
                backgroundColor = (getPicker().getValue());
                addToPalette(backgroundColor);
                getMyGuiTurtleArea().updateBackgroundColor(backgroundColor);
            }
        });
        return getPicker();
    }
}
