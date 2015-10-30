package gui.statedisplay;

import gui.turtlearea.GUITurtleAreaPaletteInterface;
import javafx.collections.MapChangeListener;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GUIPaletteDisplay extends GUIStateDisplay
        implements MapChangeListener<Integer, String> {
    private GUITurtleAreaPaletteInterface myTurtleArea;

    public GUIPaletteDisplay (GUITurtleAreaPaletteInterface turtleArea) {
        super();
        myTurtleArea = turtleArea;
        myTurtleArea.addPaletteListener(this);
    }

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
            }
        }
    }

    @Override
    public void onChanged (javafx.collections.MapChangeListener.Change<? extends Integer, ? extends String> arg0) {
        redraw();
    }
}
