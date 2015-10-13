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
    private int widthBackground=400;
    private int heightBackground=400;
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
    private void draw() {
        drawBackground();
        drawTurtles();
        drawPaths();
    }
    private void drawBackground() {
        gc.setFill(backgroundColor);
        gc.fillRect(xBackground, yBackground, widthBackground, heightBackground);
    }
    private void drawTurtles () {
        for (GUITurtle turtle: myTurtles) {
            gc.save();
            Double[] guiCoords=realToGUICoordinates(turtle.getXOnGrid(),turtle.getYOnGrid());
            setGCTransform(turtle.getAngle(), guiCoords[0], guiCoords[1]);
            gc.drawImage(turtle.getImage(), guiCoords[0], guiCoords[1]);
            gc.restore();
        }
    }
    private void drawPaths() {
        //TODO: set path colors (gc.setStroke(penColor));
        for (SlogoPath path: myPaths) {
            //TODO: be able to draw arcs
            Double[] guiStartCoords=realToGUICoordinates(path.getStart().getX(),path.getStart().getY());
            Double[] guiEndCoords=realToGUICoordinates(path.getEnd().getX(),path.getEnd().getY());
            gc.strokeLine(guiStartCoords[0], guiStartCoords[1], guiEndCoords[0], guiEndCoords[1]);;
        }
    }
    
    
    private Double[] realToGUICoordinates (double xOnGrid, double yOnGrid) {
        Double[] d = new Double[2];
        d[0]=xOnGrid+xCanvas/2;
        d[1]=yOnGrid+yCanvas/2;
        return d;
    }
    private void setGCTransform(double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
