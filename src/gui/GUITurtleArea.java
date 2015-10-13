package gui;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import util.SlogoPath;


public class GUITurtleArea extends GUIComponent implements GUITurtleAreaBGInterface {

    private List<GUITurtle> myTurtles;
    private List<SlogoPath> myPaths;
    private Color backgroundColor;
    private Canvas canvas;
    private GraphicsContext gc;
    private int xBackground=0;
    private int yBackground=0;
    private int width=400;
    private int height=400;
    private int xCanvas=400;
    private int yCanvas=400;

    public GUITurtleArea (Color turtleAreaColor, List<GUITurtle> turtles, List<SlogoPath> paths) {
        backgroundColor = turtleAreaColor;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
        myTurtles=turtles;
        myPaths=paths;
    }

    @Override
    public Node returnNodeToDraw () {
        draw();
        return canvas;
    }
    @Override
    public void updateBackgroundColor (Color c) {
        backgroundColor = c;
        draw();
    }
    public void draw() {
        drawBackground();
        drawTurtles();
        drawPaths();
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
    private void drawPaths() {
        //TODO: set path colors (gc.setStroke(penColor));
        for (SlogoPath path: myPaths) {
            //TODO: be able to draw arcs
            gc.strokeLine(path.getStart().getX(), path.getStart().getY(), path.getEnd().getX(), path.getEnd().getY());;
        }
    }
    private void setGCTransform(double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
