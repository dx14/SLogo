package gui;

import java.util.HashMap;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.SlogoPath;


public class GUITurtleAreaDrawer extends GUIComponent {
    
	private double xCanvas, yCanvas;
    private GraphicsContext gc;
    private Canvas canvas;
    private StackPane allImagesAndCanvas;
    private HashMap<Integer, ImageView> prevImages;

    public GUITurtleAreaDrawer (double width, double height) {
        xCanvas = width;
        yCanvas = height;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
        allImagesAndCanvas = new StackPane();
        
        prevImages = new HashMap<Integer, ImageView>();
    }

    public void drawBackground (Color backgroundColor) {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, xCanvas, yCanvas);
    }

    public void drawTurtleImage (GUITurtle turtle, Image image) {
        gc.save();
        Double[] offsetCoords =
                findImageCenter(turtle.getCoordinate().getX(), -1 * turtle.getCoordinate().getY(),
                                image);
        Double[] offsetguiCoords = realToGUICoordinates(offsetCoords[0], offsetCoords[1]);
        Double[] guiCoords =
                realToGUICoordinates(turtle.getCoordinate().getX(),
                                     -1 * turtle.getCoordinate().getY());
        setGCTransform(turtle.getHeading(), guiCoords[0], guiCoords[1]);
        gc.drawImage(image, offsetguiCoords[0], offsetguiCoords[1]);
        gc.restore();
    }
    
    public void drawTurtleImageView (GUITurtle turtle, Image image){
        double prevX=0;
                double prevY=0;
    	if(prevImages.containsKey(turtle.getID())){
    		allImagesAndCanvas.getChildren().remove(prevImages.get(turtle.getID()));
    	        prevX=prevImages.get(turtle.getID()).getTranslateX();
    	        prevY=-1*(prevImages.get(turtle.getID()).getTranslateY());
    	}
    	
    	
    	
    	ImageView actualImage = new ImageView(image);
    	prevImages.put(turtle.getID(), actualImage);
    	
//    	System.out.println("RRRRR"+turtle.getHistory().size());
//    	if (turtle.getHistory().size()>0) {
//    	prevX=turtle.getHistory().get(turtle.getHistory().size()-1).getStart().getX();
//    	prevY=turtle.getHistory().get(turtle.getHistory().size()-1).getStart().getY();
//    	}
//    	else {
//    	    prevX=0;
//    	    prevY=0;
//    	}

    	
        actualImage.setTranslateX(prevX);
        actualImage.setTranslateY(prevY);
        System.out.println("QEWRT"+prevY);
        actualImage.setRotate(turtle.getHeading());
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000), actualImage);
        tt.setByX(turtle.getCoordinate().getX()-prevX);
        tt.setByY(-1* (turtle.getCoordinate().getY()+prevY));
    
        
        double a = turtle.getHeading();
        boolean b = turtle.getPen().isDown();
        actualImage.setOnMouseClicked(e -> {
        	String text = "Direction: " + a + " Is down: " + b;
        	Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
            alert.showAndWait().filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> System.out.println("handled"));
        	
        });
        
        allImagesAndCanvas.getChildren().add(actualImage);
        tt.play();
    }

    public void drawPath (SlogoPath path, Color color) {
        Double[] guiStartCoords =
                realToGUICoordinates(path.getStart().getX(), -1 * path.getStart().getY());
        Double[] guiEndCoords =
                realToGUICoordinates(path.getEnd().getX(), -1 * path.getEnd().getY());
        System.out.println(path.getPen().getColor());
        gc.setStroke(color);
        gc.setLineWidth(path.getPen().getWidth());
        switch (path.getPen().getStyle()) {
            case DOTTED:
                gc.setLineDashes(1, path.getPen().getWidth() * 2);
                gc.setLineCap(StrokeLineCap.ROUND);
                break;
            case DASHED:
                gc.setLineDashes(path.getPen().getWidth() * 2, path.getPen().getWidth());
                gc.setLineCap(StrokeLineCap.BUTT);
                break;
            default:
                gc.setLineDashes(0);
                gc.setLineCap(StrokeLineCap.BUTT);
                break;
        }
        gc.strokeLine(guiStartCoords[0], guiStartCoords[1], guiEndCoords[0], guiEndCoords[1]);
    }

    // ___helper functions___
    private Double[] realToGUICoordinates (double xOnGrid, double yOnGrid) {
        Double[] d = new Double[2];
        d[0] = xOnGrid + xCanvas / 2;
        d[1] = yOnGrid + yCanvas / 2;
        return d;
    }

    private Double[] findImageCenter (double xOnGrid, double yOnGrid, Image image) {
        Double[] d = new Double[2];
        d[0] = xOnGrid - image.getWidth() / 2;
        d[1] = yOnGrid - image.getHeight() / 2;
        return d;
    }

    private void setGCTransform (double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    @Override
    public Node returnNodeToDraw () {
    	
    	   allImagesAndCanvas.getChildren().add(canvas);
    	
        return allImagesAndCanvas;
    }
}