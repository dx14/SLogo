package gui.statedisplay;

import gui.turtlearea.GUITurtleAreaImagesInterface;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class GUIImageDisplay extends GUIStateDisplay implements ListChangeListener {
    private GUITurtleAreaImagesInterface myTurtleArea;

    public GUIImageDisplay (GUITurtleAreaImagesInterface turtleArea) {
        myTurtleArea = turtleArea;
        myTurtleArea.addImagesListener(this);
    }

    @Override
    public void redraw () {
        getHbox().getChildren().clear();
        int max = myTurtleArea.getImagesSize();
        for (int i = 0; i < max; i++) {
            getHbox().getChildren().add(new Text(i + "->"));
            getHbox().getChildren().add(new ImageView(myTurtleArea.parseImage(i)));
        }
    }

    @Override
    public void onChanged (Change arg0) {
        redraw();
    }
}
