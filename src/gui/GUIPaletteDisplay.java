package gui;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.MapChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GUIPaletteDisplay extends GUIComponent implements MapChangeListener{
    GUITurtleAreaPaletteInterface myTurtleArea;
    ScrollPane pane;
    HBox hbox;
    public GUIPaletteDisplay(GUITurtleAreaPaletteInterface turtleArea) {
        myTurtleArea=turtleArea;
        hbox = new HBox();
        pane = new ScrollPane(hbox);
        myTurtleArea.addPaletteListener(this);
    }
    public void redraw() {
        hbox.getChildren().clear();
        for (Integer i: myTurtleArea.getColorMap().keySet()) {
            hbox.getChildren().add(new Text(i + "->"));
            Rectangle rect = new Rectangle(20,20);
            rect.setFill(Paint.valueOf(myTurtleArea.getColorMap().get(i)));
            hbox.getChildren().add(rect);
        }
    }
    @Override
    public Node returnNodeToDraw () {
        redraw();
        return pane;
    }
    @Override
    public void onChanged (Change arg0) {
        redraw();
    }
}
