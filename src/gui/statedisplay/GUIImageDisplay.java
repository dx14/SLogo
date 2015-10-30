package gui.statedisplay;

import gui.turtlearea.GUITurtleAreaImagesInterface;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


/**
 * The Class GUIImageDisplay extends GUIStateDisplay and displays the image "palette".
 * Also implements ListChangeListener to update the display when the image palette is changed.
 * @author John
 */
public class GUIImageDisplay extends GUIStateDisplay implements ListChangeListener<String> {
    private GUITurtleAreaImagesInterface myTurtleArea;

    /**
     * Instantiates a new GUI image display.
     *
     * @param turtleArea the turtle area
     */
    public GUIImageDisplay (GUITurtleAreaImagesInterface turtleArea) {
        myTurtleArea = turtleArea;
        myTurtleArea.addImagesListener(this);
    }

    /* (non-Javadoc)
     * @see gui.statedisplay.GUIStateDisplay#redraw()
     */
    @Override
    public void redraw () {
        getHbox().getChildren().clear();
        int max = myTurtleArea.getImagesSize();
        for (int i = 0; i < max; i++) {
            getHbox().getChildren()
                    .add(new Text(i + getTextResources().getString("imagedelimiter")));
            getHbox().getChildren().add(new ImageView(myTurtleArea.parseImage(i)));
        }
    }

    /* (non-Javadoc)
     * @see javafx.collections.ListChangeListener#onChanged(javafx.collections.ListChangeListener.Change)
     */
    @Override
    public void onChanged (javafx.collections.ListChangeListener.Change<? extends String> arg0) {
        redraw();
    }

}
