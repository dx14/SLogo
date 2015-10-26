package gui;

import java.util.Map.Entry;
import com.sun.javafx.scene.control.skin.ColorPalette;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public abstract class GUIPalette extends GUIComponent {

    private ColorPalette palette;
    private GUITurtleAreaPaletteInterface myGuiTurtleArea;


    public GUIPalette (GUITurtleAreaBGInterface guiTurtleArea) {
        ColorPicker picker = new ColorPicker();
        palette = new ColorPalette(picker);
        setMyGuiTurtleArea((GUITurtleAreaPaletteInterface) guiTurtleArea);
    }


    public GUITurtleAreaPaletteInterface getMyGuiTurtleArea () {
        return myGuiTurtleArea;
    }


    public void setMyGuiTurtleArea (GUITurtleAreaPaletteInterface myGuiTurtleArea) {
        this.myGuiTurtleArea = myGuiTurtleArea;
    }
    public int addToPalette(Color penColor) {
        int i=0;
        if (!getMyGuiTurtleArea().getColorMap().containsValue(penColor.toString())) {
            while (getMyGuiTurtleArea().getColorMap().containsKey(i)) {
                i++;
            }
            getMyGuiTurtleArea().getColorMap().put(i, penColor.toString());
        }
        else {
            for (Entry<Integer,String> en: getMyGuiTurtleArea().getColorMap().entrySet()) {
                if (penColor.toString().equals(en.getValue())) {
                    i=en.getKey();
                    break;
                }
            }
        }
        return i;
    }
    
    private static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
}
