package gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import parser.structure.GUITurtleContainer;
import parser.structure.TurtleContainer;
import parser.structure.VariableContainer;
import util.GUIVariable;
import util.SlogoPath;


public class GUITurtleArea extends GUIComponent implements GUITurtleAreaBGInterface, GUITurtleAreaRedrawInterface, Observer {

    private List<GUITurtle> myTurtles;
    private List<SlogoPath> myPaths;
    private Color backgroundColor;
    private Canvas canvas;
    private GraphicsContext gc;
    private double xCanvas=400;
    private double yCanvas=400;
    private int xBackground=0;
    private int yBackground=0;
    private double widthBackground=xCanvas;
    private double heightBackground=yCanvas;

    public GUITurtleArea (Stage mainStage, Color turtleAreaColor, List<GUITurtle> turtles, List<SlogoPath> paths) {
        //TODO: figure out a better way to set the below values
        xCanvas=mainStage.getScene().getWidth()-300;
        widthBackground=xCanvas;
        yCanvas=mainStage.getScene().getHeight()-200;
        heightBackground=yCanvas;

        backgroundColor = turtleAreaColor;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
        myTurtles=turtles;
        myPaths=paths;
    }

    @Override
    public Node returnNodeToDraw () {
        drawAll();
        return canvas;
    }
    @Override
    public void updateBackgroundColor (Color c) {
        backgroundColor = c;
        drawAll();
    }
    public void drawAll() {
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
            Double[] offsetCoords=findImageCenter(turtle.getXOnGrid(),turtle.getYOnGrid(), turtle.getImage());
            Double[] offsetguiCoords=realToGUICoordinates(offsetCoords[0],offsetCoords[1]);
            Double[] guiCoords=realToGUICoordinates(turtle.getXOnGrid(),turtle.getYOnGrid());
            setGCTransform(turtle.getAngle(), guiCoords[0], guiCoords[1]);
            gc.drawImage(turtle.getImage(), offsetguiCoords[0], offsetguiCoords[1]);
            gc.restore();
        }
    }
    private void drawTurtle (GUITurtle turtle) {
            gc.save();
            Double[] offsetCoords=findImageCenter(turtle.getXOnGrid(),turtle.getYOnGrid(), turtle.getImage());
            Double[] offsetguiCoords=realToGUICoordinates(offsetCoords[0],offsetCoords[1]);
            Double[] guiCoords=realToGUICoordinates(turtle.getXOnGrid(),turtle.getYOnGrid());
            setGCTransform(turtle.getAngle(), guiCoords[0], guiCoords[1]);
            gc.drawImage(turtle.getImage(), offsetguiCoords[0], offsetguiCoords[1]);
            gc.restore();
    }
    private void drawPaths() {
        //TODO: set path colors (gc.setStroke(penColor));
        for (SlogoPath path: myPaths) {
            //TODO: be able to draw arcs
            Double[] guiStartCoords=realToGUICoordinates(path.getStart().getX(),path.getStart().getY());
            Double[] guiEndCoords=realToGUICoordinates(path.getEnd().getX(),path.getEnd().getY());
            //gc.setStroke(path.getPenColor());
            gc.strokeLine(guiStartCoords[0], guiStartCoords[1], guiEndCoords[0], guiEndCoords[1]);
        }
    }
    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub
        if(o instanceof TurtleContainer){
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            drawTurtle(turtle);
    }
    
    else{
            //make this an exception
            System.out.println("update didnt update it; it might not be an instance of GUIVariableContainer");
    }
    }
    
    //___helper functions___
    private Double[] realToGUICoordinates (double xOnGrid, double yOnGrid) {
        Double[] d = new Double[2];
        d[0]=xOnGrid+xCanvas/2;
        d[1]=yOnGrid+yCanvas/2;
        return d;
    }
    private Double[] findImageCenter (double xOnGrid, double yOnGrid, Image image) {
        Double[] d = new Double[2];
        d[0]=xOnGrid-image.getWidth()/2;
        d[1]=yOnGrid-image.getHeight()/2;
        return d;
    }
    private void setGCTransform(double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

}
