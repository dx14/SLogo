package gui.turtlearea;

import java.util.Map;
import javafx.collections.MapChangeListener;


/**
 * The Interface GUITurtleAreaPaletteInterface extends GUITurtleAreaBGInterface and
 * is used for classes needing to interface with the local color palette.
 * @author John
 */
public interface GUITurtleAreaPaletteInterface extends GUITurtleAreaBGInterface {
    
    /**
     * Gets the color map.
     *
     * @return the color map
     */
    public Map<Integer, String> getColorMap ();

    /**
     * Adds the palette listener.
     *
     * @param m the listener
     */
    public void addPaletteListener (MapChangeListener<Integer, String> m);
}
