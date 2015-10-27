package gui.turtlearea;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;


public interface GUITurtleAreaImagesInterface extends GUITurtleAreaRedrawInterface {
    public Image parseImage (int index);

    public void addImagesListener (ListChangeListener l);
}
