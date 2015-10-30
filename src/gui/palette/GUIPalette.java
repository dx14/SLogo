package gui.palette;

import java.util.Map.Entry;
import gui.GUIComponent;
import gui.turtlearea.GUITurtleAreaBGInterface;
import gui.turtlearea.GUITurtleAreaPaletteInterface;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


/**
 * The abstract Class GUIPalette extends GUIComponent and contains information about colors and a color picker.
 * @author John
 */
public abstract class GUIPalette extends GUIComponent {

    private GUITurtleAreaPaletteInterface myGuiTurtleArea;
    private final ColorPicker picker;

    /**
     * Instantiates a new GUI palette.
     *
     * @param guiTurtleArea the associated GUITurtleArea
     */
    protected GUIPalette (GUITurtleAreaBGInterface guiTurtleArea) {
        picker = new ColorPicker();
        setMyGuiTurtleArea((GUITurtleAreaPaletteInterface) guiTurtleArea);
    }

    /**
     * Gets the gui turtle area.
     *
     * @return the gui turtle area
     */
    public GUITurtleAreaPaletteInterface getMyGuiTurtleArea () {
        return myGuiTurtleArea;
    }

    /**
     * Sets the gui turtle area.
     *
     * @param myGuiTurtleArea the new gui turtle area
     */
    public void setMyGuiTurtleArea (GUITurtleAreaPaletteInterface myGuiTurtleArea) {
        this.myGuiTurtleArea = myGuiTurtleArea;
    }

    /**
     * Adds the color to palette.
     *
     * @param penColor the pen color
     * @return the index of the new color
     */
    public int addToPalette (Color penColor) {
        int i = 0;
        if (!getMyGuiTurtleArea().getColorMap().containsValue(penColor.toString())) {
            while (getMyGuiTurtleArea().getColorMap().containsKey(i)) {
                i++;
            }
            getMyGuiTurtleArea().getColorMap().put(i, penColor.toString());
        }
        else {
            for (Entry<Integer, String> en : getMyGuiTurtleArea().getColorMap().entrySet()) {
                if (penColor.toString().equals(en.getValue())) {
                    i = en.getKey();
                    break;
                }
            }
        }
        return i;
    }

    /**
     * To rgb code.
     *
     * @param color the color
     * @return the string representation of the color in the format "#XXXXXX"
     */
    public static String toRGBCode (Color color) {
        return String.format("#%02X%02X%02X",
                             (int) (color.getRed() * 255),
                             (int) (color.getGreen() * 255),
                             (int) (color.getBlue() * 255));
    }

    /**
     * Gets the picker.
     *
     * @return the picker
     */
    public ColorPicker getPicker () {
        return picker;
    }
}
