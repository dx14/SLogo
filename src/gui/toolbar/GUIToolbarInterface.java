package gui.toolbar;

import java.util.List;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;


public interface GUIToolbarInterface {
    public String getPalette ();

    public String getBGColor ();

    public String getImageList ();


    public List<GUITurtle> getTurtles ();

    public GUITurtleAreaRedrawInterface getTurtleArea ();
    public SLogoLanguage getCurrentLanguage ();
    public void setCurrentLanguage (String lang);
    public SLogoLanguage getDefaultLanguage ();
    public GUIController getController ();
}
