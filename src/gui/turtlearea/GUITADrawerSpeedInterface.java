package gui.turtlearea;

/**
 * The Interface GUITADrawerSpeedInterface used for classes needing to interface with the turtle area animation speed.
 * @author John
 */
public interface GUITADrawerSpeedInterface {
    
    /**
     * Sets the speed.
     *
     * @param speed the new speed
     */
    public void setSpeed (double speed);

    /**
     * Gets the speed.
     *
     * @return the speed
     */
    public double getSpeed ();
}
