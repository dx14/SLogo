package gui.toolbar;

import java.util.List;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.turtlearea.GUITurtleAreaRedrawInterface;


/**
 * The Interface GUIToolbarInterface provides a few getter and setter methods for accessing from external classes.
 * @author John
 */
public interface GUIToolbarInterface {
    
    /**
     * Gets the palette.
     *
     * @return the palette
     */
    public String getPalette ();

    /**
     * Gets the BG color.
     *
     * @return the BG color
     */
    public String getBGColor ();

    /**
     * Gets the image list.
     *
     * @return the image list
     */
    public String getImageList ();

    /**
     * Gets the turtles.
     *
     * @return the turtles
     */
    public List<GUITurtle> getTurtles ();

    /**
     * Gets the turtle area.
     *
     * @return the turtle area
     */
    public GUITurtleAreaRedrawInterface getTurtleArea ();

    /**
     * Gets the current language.
     *
     * @return the current language
     */
    public SLogoLanguage getCurrentLanguage ();

    /**
     * Sets the current language.
     *
     * @param lang the new current language
     */
    public void setCurrentLanguage (String lang);

    /**
     * Gets the default language.
     *
     * @return the default language
     */
    public SLogoLanguage getDefaultLanguage ();

    /**
     * Gets the controller.
     *
     * @return the controller
     */
    public GUIController getController ();
}
