package gui;

import java.util.Observable;
import java.util.Observer;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import parser.structure.TurtleContainer;

public class GUIImageDisplay extends GUIComponent implements ListChangeListener{
    GUITurtleAreaImagesInterface myTurtleArea;
    ScrollPane pane;
    HBox hbox;
    public GUIImageDisplay(GUITurtleAreaImagesInterface turtleArea) {
        myTurtleArea=turtleArea;
        hbox = new HBox();
        pane = new ScrollPane(hbox);
        myTurtleArea.addImagesListener(this);
    }
    public void redraw() {
        hbox.getChildren().clear();
        int max = myTurtleArea.getImagesSize();
        for (int i=0; i<max;i++) {
            hbox.getChildren().add(new Text(i + "->"));
            hbox.getChildren().add(new ImageView(myTurtleArea.parseImage(i)));
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
