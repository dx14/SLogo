package gui.turtlearea;

import java.util.Map;
import javafx.collections.MapChangeListener;


public interface GUITurtleAreaPaletteInterface extends GUITurtleAreaBGInterface {
    public Map<Integer, String> getColorMap ();

    public void addPaletteListener (MapChangeListener l);
}
