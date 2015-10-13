package gui;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


public class GUITurtleArea extends GUIComponent implements GUITurtleAreaBGInterface {

    private List<GUITurtle> myTurtles;
    private Color backgroundColor;
    private Canvas canvas;
    private GraphicsContext gc;
    private int xBackground=0;
    private int yBackground=0;
    private int width=400;
    private int height=400;
    private int xCanvas=400;
    private int yCanvas=400;
    private GUIController myGUIController;

    public GUITurtleArea (Color turtleAreaColor, List<GUITurtle> turtles) {
        backgroundColor = turtleAreaColor;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
        myTurtles=turtles;
    }

    @Override
    public Node returnNodeToDraw () {
        drawBackground();
        drawTurtles();
        return canvas;
    }
    @Override
    public void updateBackgroundColor (Color c) {
        backgroundColor = c;
        drawBackground();
    }
    public void drawBackground() {
        gc.setFill(backgroundColor);
        gc.fillRect(xBackground, yBackground, width, height);
    }
    private void drawTurtles () {
        for (GUITurtle turtle: myTurtles) {
            gc.save();
            setGCTransform(turtle.getAngle(), turtle.getXOnGrid(), turtle.getYOnGrid());
            gc.drawImage(turtle.getImage(), turtle.getXOnGrid(), turtle.getYOnGrid());
            gc.restore();
        }
    }
    private void setGCTransform(double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
