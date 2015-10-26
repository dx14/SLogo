package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import parser.structure.GUITurtleContainer;
import parser.structure.TurtleContainer;
import parser.structure.VariableContainer;
import util.GUIVariable;
import util.SlogoPath;


public class GUITurtleArea extends GUIComponent implements GUITurtleAreaBGInterface, GUITurtleAreaRedrawInterface, Observer {

    private static final double DASH_LENGTH = 10;
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
    private String imageFileDirectoryName;
    private List<String> images;
    private int numResourceImages;
    private final String TURTLE_DISPLAY_FILE = "src/resources/guitext/TurtleDisplay.properties";


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
        setTextResources(ResourceBundle.getBundle("resources.guitext.TurtleDisplay"));
        images = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TURTLE_DISPLAY_FILE)));
            while (scanner.hasNextLine()) {
                images.add(scanner.nextLine());
            }
            numResourceImages=images.size();
        }
        catch (FileNotFoundException e) {
            handleException(e);
        }
        scanner.close();
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
            drawTurtle(turtle);
        }
    }
    private void drawTurtle (GUITurtle turtle) {
    	System.out.println("DRAWING TURTLE");
        try {
            Image image;
            if (turtle.getDisplayIndex()<numResourceImages) {
                System.out.println("less than");
                image = new Image(images.get(turtle.getDisplayIndex()));
            }
            else {
                image = new Image("file:"+images.get(turtle.getDisplayIndex()));
                System.out.println(String.valueOf(turtle.getDisplayIndex()));
            }
            drawTurtleImage(turtle, image);
        }
        catch (Exception e) {
                handleException(e);
                Image image = new Image("http://el.media.mit.edu/logo-foundation/what_is_logo/graphics/image4.jpg");
                drawTurtleImage(turtle, image);
        }            
    }
    private void drawTurtleImage(GUITurtle turtle, Image image) {
        gc.save();
        Double[] offsetCoords=findImageCenter(turtle.getCoordinate().getX(),-1*turtle.getCoordinate().getY(), image);
        Double[] offsetguiCoords=realToGUICoordinates(offsetCoords[0],offsetCoords[1]);
        Double[] guiCoords=realToGUICoordinates(turtle.getCoordinate().getX(),-1*turtle.getCoordinate().getY());
        setGCTransform(turtle.getHeading(), guiCoords[0], guiCoords[1]);
        gc.drawImage(image, offsetguiCoords[0], offsetguiCoords[1]);
        gc.restore();
    }
    private void turtleObserved(GUITurtle turtle) {
        myTurtles.clear();
        if (turtle.isShowing()) {
        	System.out.println("TURTLE IS SHOWING");
            myTurtles.add(turtle);
        }
        myPaths.clear();
        if (!turtle.isClear()) {
            for (SlogoPath path: turtle.getHistory()) {
                checkPen(path);
            }
            for (SlogoPath path: turtle.getPaths()) {
                checkPen(path);
            }
        }
        drawAll();
        //myPaths.stream().forEach(s -> System.out.println(s.toString()));
        //        if (turtle.getPaths().size()>0){
        //            for(SlogoPath p : turtle.getPaths()){
        //                drawPath(p);
        //            }
        //        }
    }
    private void checkPen(SlogoPath path) {
        if (path.getPen().isDown()) {
            myPaths.add(path);
        }
    }
    private void drawPaths() {
        //TODO: set path colors (gc.setStroke(penColor));
        for (SlogoPath path: myPaths) {
            drawPath(path);
        }
    }
    
    // TODO: 
    private void drawPath(SlogoPath path) {
        //TODO: be able to draw arcs
        Double[] guiStartCoords=realToGUICoordinates(path.getStart().getX(),-1*path.getStart().getY());
        Double[] guiEndCoords=realToGUICoordinates(path.getEnd().getX(),-1*path.getEnd().getY());
        // TODO: replace this with a function to look up the color index
        gc.setStroke(Color.valueOf("#000000"));
        gc.setLineWidth(path.getPen().getWidth());
        switch (path.getPen().getStyle()) {
            case DOTTED:
                gc.setLineDashes(1,path.getPen().getWidth());
                gc.setLineCap(StrokeLineCap.ROUND);
                break;
            case DASHED:
                gc.setLineDashes(DASH_LENGTH,DASH_LENGTH);
                gc.setLineCap(StrokeLineCap.SQUARE);
                break;
            default:
                gc.setLineDashes(0);
                gc.setLineCap(StrokeLineCap.SQUARE);
                break;
        }
        System.out.println(path.getPen().getStyle().toString());
        gc.strokeLine(guiStartCoords[0], guiStartCoords[1], guiEndCoords[0], guiEndCoords[1]);
    }
    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub
        if(o instanceof TurtleContainer){
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            //System.out.println("OUTPUTTING");
            turtleObserved(turtle);
            turtle.completeUpdate();
        }

        else{
            //make this an exception
            System.out.println("update didnt update it; it might not be an instance of GUITurtleContainer");
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

    @Override
    public List<String> getImages () {
        return images;
    }

}
