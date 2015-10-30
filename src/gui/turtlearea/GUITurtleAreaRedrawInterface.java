package gui.turtlearea;

// TODO: Auto-generated Javadoc
/**
 * The Interface GUITurtleAreaRedrawInterface used for classes needing to interface with the turtle area display.
 * @author John
 */
public interface GUITurtleAreaRedrawInterface {

    /**
     * Draws the turtles and paths.
     */
    public void drawAll ();

    /**
     * Adds the image.
     *
     * @param image the image
     */
    public void addImage (String image);

    /**
     * Gets the images size.
     *
     * @return the images size
     */
    public int getImagesSize ();

    /**
     * Gets the BG color.
     *
     * @return the BG color
     */
    public String getBGColor ();

    /**
     * Gets the image paths as a string.
     *
     * @return the image paths as a string
     */
    public String getImagePathsAsAString ();

    /**
     * Gets the palette.
     *
     * @return the palette
     */
    public String getPalette ();
}
