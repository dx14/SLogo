package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public class GUIPaletteBackground extends GUIPalette {

    private Color backgroundColor;

    public GUIPaletteBackground (Color col, GUITurtleAreaBGInterface guiTurtleArea) {
        super(guiTurtleArea);
        backgroundColor = col;
    }

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
