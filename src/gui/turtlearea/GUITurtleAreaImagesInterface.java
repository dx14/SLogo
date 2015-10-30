package gui.turtlearea;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;


/**
 * The Interface GUITurtleAreaImagesInterface extends GUITurtleAreaRedrawInterface and
 * is used for classes needing to interface with the image palette.
 * @author John
 */
public interface GUITurtleAreaImagesInterface extends GUITurtleAreaRedrawInterface {
    
    /**
     * Returns the image corresponding to the index.
     *
     * @param index the index
     * @return the image
     */
    public Image parseImage (int index);

    /**
     * Adds the images listener.
     *
     * @param l the listener
     */
    public void addImagesListener (ListChangeListener<String> l);
}
