package gui.statedisplay;

import gui.turtlearea.GUITurtleAreaPaletteInterface;
import javafx.collections.MapChangeListener;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


// TODO: Auto-generated Javadoc
/**
 * The Class GUIPaletteDisplay extends GUIStateDisplay and displays the local color "palette".
 * Also implements MapChangeListener to react to changes in the color palette.
 * @author John
 */
public class GUIPaletteDisplay extends GUIStateDisplay
        implements MapChangeListener<Integer, String> {
    private GUITurtleAreaPaletteInterface myTurtleArea;

    /**
     * Instantiates a new GUI palette display.
     *
     * @param turtleArea the turtle area
     */
    public GUIPaletteDisplay (GUITurtleAreaPaletteInterface turtleArea) {
        super();
        myTurtleArea = turtleArea;
        myTurtleArea.addPaletteListener(this);
    }

    /* (non-Javadoc)
     * @see gui.statedisplay.GUIStateDisplay#redraw()
     */
    @Override
    public void redraw () {
        getHbox().getChildren().clear();
        for (Integer i : myTurtleArea.getColorMap().keySet()) {
            getHbox().getChildren()
                    .add(new Text(i + getTextResources().getString("imagedelimiter")));
            try {
                double size = Double.parseDouble(getTextResources().getString("paletteiconsize"));
                Rectangle rect = new Rectangle(size, size);
                rect.setFill(Paint.valueOf(myTurtleArea.getColorMap().get(i)));
                getHbox().getChildren().add(rect);
            }
            catch (Exception e) {
                handleException(e);
            }
        }
    }

    /* (non-Javadoc)
     * @see javafx.collections.MapChangeListener#onChanged(javafx.collections.MapChangeListener.Change)
     */
    @Override
    public void onChanged (javafx.collections.MapChangeListener.Change<? extends Integer, ? extends String> arg0) {
        redraw();
    }
}
