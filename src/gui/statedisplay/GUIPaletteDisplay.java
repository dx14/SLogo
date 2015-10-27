package gui.statedisplay;

import gui.turtlearea.GUITurtleAreaPaletteInterface;
import javafx.collections.MapChangeListener;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GUIPaletteDisplay extends GUIStateDisplay implements MapChangeListener {
    private GUITurtleAreaPaletteInterface myTurtleArea;

    public GUIPaletteDisplay (GUITurtleAreaPaletteInterface turtleArea) {
        super();
        myTurtleArea = turtleArea;
        myTurtleArea.addPaletteListener(this);
    }

    @Override
    public void redraw () {
        getHbox().getChildren().clear();
        for (Integer i : myTurtleArea.getColorMap().keySet()) {
            getHbox().getChildren().add(new Text(i + "->"));
            Rectangle rect = new Rectangle(20, 20);
            rect.setFill(Paint.valueOf(myTurtleArea.getColorMap().get(i)));
            getHbox().getChildren().add(rect);
        }
    }

    @Override
    public void onChanged (Change arg0) {
        redraw();
    }
}
