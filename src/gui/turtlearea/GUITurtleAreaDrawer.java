package gui.turtlearea;

import java.util.HashMap;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import util.SlogoPath;


/**
 * The Class GUITurtleAreaDrawer extends GUIComponent and takes care of the actual drawing of the turtle area.
 * @author John, Efe
 */
public class GUITurtleAreaDrawer extends GUIComponent {

    private double xCanvas, yCanvas;
    private GraphicsContext gc;
    private Canvas canvas;
    private StackPane allImagesAndCanvas;
    private HashMap<Integer, ImageView> prevImages;
    private double mySpeed;
    private GUIController controller;

    /**
     * Instantiates a new GUI turtle area drawer.
     *
     * @param width the width
     * @param height the height
     * @param myController the my controller
     */
    public GUITurtleAreaDrawer (double width, double height, GUIController myController) {
        setTextResources(ResourceBundle.getBundle("resources.guitext.TurtleDrawer"));
        xCanvas = width;
        yCanvas = height;
        canvas = new Canvas(xCanvas, yCanvas);
        gc = canvas.getGraphicsContext2D();
        allImagesAndCanvas = new StackPane();
        allImagesAndCanvas.getChildren().add(canvas);
        controller = myController;
        mySpeed = Double.parseDouble(getTextResources().getString("animationspeed"));
        prevImages = new HashMap<Integer, ImageView>();
    }

    /**
     * Draw background.
     *
     * @param backgroundColor the background color
     */
    public void drawBackground (Color backgroundColor) {
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, xCanvas, yCanvas);
    }

    /**
     * Draw turtle image.
     * 
     * @deprecated using legacy Image class
     * @param turtle the turtle
     * @param image the image
     */
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

    /**
     * Draw turtle image view.
     *
     * @param turtle the turtle
     * @param image the image
     */
    public void drawTurtleImageView (GUITurtle turtle, Image image) {
        double prevX = 0;
        double prevY = 0;
        if (prevImages.containsKey(turtle.getID())) {
            allImagesAndCanvas.getChildren().remove(prevImages.get(turtle.getID()));
            prevX = prevImages.get(turtle.getID()).getTranslateX();
            prevY = -1 * (prevImages.get(turtle.getID()).getTranslateY());
        }
        ImageView actualImage = new ImageView(image);
        prevImages.put(turtle.getID(), actualImage);
        actualImage.setTranslateX(prevX);
        actualImage.setTranslateY(-1 * prevY);
        actualImage.setRotate(turtle.getHeading());
        TranslateTransition tt = new TranslateTransition(Duration.millis(mySpeed), actualImage);
        tt.setByX(turtle.getCoordinate().getX() - prevX);
        tt.setByY(-1 * (turtle.getCoordinate().getY() - prevY));
        double heading = turtle.getHeading();
        boolean down = turtle.getPen().isDown();
        double x = turtle.getCoordinate().getX();
        double y = turtle.getCoordinate().getY();
        int id = turtle.getID();
        actualImage.setOnMouseClicked(e -> {
            try {
                controller.runCommand("tell [ " + turtle.getID() + " ]");
            }
            catch (Exception e1) {
            }
            String text = getTextResources().getString("id") + id + "; " +
                          getTextResources().getString("coord") + x + "," + y + "; " +
                          getTextResources().getString("direction") + heading + "; " +
                          getTextResources().getString("isdown") + down;
            Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
            alert.showAndWait();
        });
        allImagesAndCanvas.getChildren().add(actualImage);
        tt.play();
    }

    /**
     * Draw path.
     *
     * @param path the path
     * @param color the color
     */
    public void drawPath (SlogoPath path, Color color) {
        Double[] guiStartCoords =
                realToGUICoordinates(path.getStart().getX(), -1 * path.getStart().getY());
        Double[] guiEndCoords =
                realToGUICoordinates(path.getEnd().getX(), -1 * path.getEnd().getY());
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

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        return allImagesAndCanvas;
    }

    /**
     * Sets the animation speed.
     *
     * @param speed the new speed
     */
    public void setSpeed (double speed) {
        mySpeed = speed;
    }

    /**
     * Gets the animation speed.
     *
     * @return the speed
     */
    public double getSpeed () {
        return mySpeed;
    }

    /**
     * Real to gui coordinates.
     *
     * @param xOnGrid the x on grid
     * @param yOnGrid the y on grid
     * @return the double[]
     */
    // ___helper functions___
    private Double[] realToGUICoordinates (double xOnGrid, double yOnGrid) {
        Double[] guiCoords = new Double[2];
        guiCoords[0] = xOnGrid + xCanvas / 2;
        guiCoords[1] = yOnGrid + yCanvas / 2;
        return guiCoords;
    }

    /**
     * Find image center.
     *
     * @param xOnGrid the x on grid
     * @param yOnGrid the y on grid
     * @param image the image
     * @return the double[]
     */
    private Double[] findImageCenter (double xOnGrid, double yOnGrid, Image image) {
        Double[] d = new Double[2];
        d[0] = xOnGrid - image.getWidth() / 2;
        d[1] = yOnGrid - image.getHeight() / 2;
        return d;
    }

    /**
     * Sets the graphics context rotation transform.
     *
     * @param angle the angle
     * @param x the x
     * @param y the y
     */
    private void setGCTransform (double angle, double x, double y) {
        Rotate r = new Rotate(angle, x, y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
