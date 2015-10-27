package gui.toolbar;

import java.util.List;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;

public interface GUIToolbarInterface {
    public String getPalette ();
    public String getBGColor ();
    public String getImageList ();
    public String getLanguage ();
    public List<GUITurtle> getTurtles ();
    public GUITurtleAreaRedrawInterface getTurtleArea();
}
