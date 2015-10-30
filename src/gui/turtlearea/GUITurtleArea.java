package gui.turtlearea;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import gui.GUIComponent;
import gui.modelinterface.GUIController;
import gui.modelinterface.GUITurtle;
import gui.modelinterface.GUITurtleContainer;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import parser.structure.TurtleContainer;
import util.SlogoPath;


/**
 * The Class GUITurtleArea extends GUIComponent and represents the central turtle area display.
 * It also holds many of the front end variables including the turtle, path, and local palette lists.
 * Instatiates a GUITurtleAreaDrawer to do the drawing.
 * Implements GUITurtleAreaImangesInterface, GUITurtleAreaPaletteInterface, and GUITADrawerSpeedInterface for communication.
 * Implements Observer to update whenever turtles are changed in the backend
 * @author John
 */
public class GUITurtleArea extends GUIComponent
        implements GUITurtleAreaImagesInterface, GUITurtleAreaPaletteInterface,
        GUITADrawerSpeedInterface, Observer {

    private List<GUITurtle> myTurtles;
    private List<SlogoPath> myPaths;
    private Color backgroundColor;
    private double xCanvas;
    private double yCanvas;
    private ObservableList<String> images;
    private ObservableMap<Integer, String> palette;
    private static final String TURTLE_AREA_FILE = "resources.guitext.TurtleArea";
    private GUITurtleAreaDrawer drawer;

    /**
     * Instantiates a new GUI turtle area.
     *
     * @param mainStage the main stage
     * @param turtleAreaColor the turtle area color
     * @param turtles the turtles
     * @param paths the paths
     * @param imagePaths the image paths
     * @param defaultPalette the default palette
     * @param controller the controller
     */
    public GUITurtleArea (Stage mainStage,
                          Color turtleAreaColor,
                          List<GUITurtle> turtles,
                          List<SlogoPath> paths,
                          List<String> imagePaths,
                          Map<Integer, String> defaultPalette,
                          GUIController controller) {
        setTextResources(ResourceBundle.getBundle(TURTLE_AREA_FILE));
        xCanvas =
                mainStage.getScene().getWidth() -
                  Integer.parseInt(getTextResources().getString("canvasxshrinkpixels"));
        yCanvas =
                mainStage.getScene().getHeight() -
                  Integer.parseInt(getTextResources().getString("canvasyshrinkpixels"));
        drawer = new GUITurtleAreaDrawer(xCanvas, yCanvas, controller);
        drawer.setSpeed(Double.parseDouble(getTextResources().getString("initialspeed")));
        backgroundColor = turtleAreaColor;
        myTurtles = turtles;
        myPaths = paths;
        images = FXCollections.observableArrayList(imagePaths);
        palette = FXCollections.observableHashMap();
        drawer.drawBackground(backgroundColor);
        defaultPalette.keySet().stream().forEach(e -> palette.put(e, defaultPalette.get(e)));
    }

    /* (non-Javadoc)
     * @see gui.GUIComponent#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        drawAll();
        return drawer.returnNodeToDraw();
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaBGInterface#updateBackgroundColor(javafx.scene.paint.Color)
     */
    @Override
    public void updateBackgroundColor (Color c) {
        backgroundColor = c;
        drawer.drawBackground(backgroundColor);
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#drawAll()
     */
    @Override
    public void drawAll () {
        drawTurtles();
        drawPaths();
    }

    /**
     * Draw all turtles.
     */
    private void drawTurtles () {
        myTurtles.stream().forEach(t -> drawTurtle(t));
    }

    /**
     * Draw a turtle.
     *
     * @param turtle the turtle
     */
    private void drawTurtle (GUITurtle turtle) {
        Image image;
        try {
            image = parseImage(turtle.getDisplayIndex());
            drawer.drawTurtleImageView(turtle, image);
        }
        catch (Exception e) {
            handleException(e);
            image =
                    new Image((getTextResources().getString("defaultimageurl")));
            drawer.drawTurtleImageView(turtle, image);
        }
    }

    /**
     * Turtle observed, called when update() observed.
     *
     * @param turtle the turtle
     */
    private void turtleObserved (GUITurtle turtle) {
        myTurtles.clear();
        if (turtle.isShowing()) {
            myTurtles.add(turtle);
        }
        myPaths.clear();
        if (!turtle.isClear()) {
            turtle.getHistory().stream().forEach(p -> checkPen(p));
            turtle.getPaths().stream().forEach(p -> checkPen(p));
        }
        drawAll();
    }

    /**
     * Check pen.
     *
     * @param path the path
     */
    private void checkPen (SlogoPath path) {
        if (path.getPen().isDown()) {
            myPaths.add(path);
        }
    }

    /**
     * Draw paths.
     */
    private void drawPaths () {
        for (SlogoPath path : myPaths) {
            drawer.drawPath(path, Color.valueOf(palette.get(path.getPen().getColor())));
        }
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            turtleObserved(turtle);
            turtle.completeUpdate();
        }
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#addImage(java.lang.String)
     */
    @Override
    public void addImage (String image) {
        images.add(image);
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#getImagesSize()
     */
    @Override
    public int getImagesSize () {
        return images.size();
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#getBGColor()
     */
    @Override
    public String getBGColor () {
        return backgroundColor.toString();
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#getImagePathsAsAString()
     */
    @Override
    public String getImagePathsAsAString () {
        return images.stream().reduce( (t, u) -> t + "," + u).get();
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaImagesInterface#parseImage(int)
     */
    @Override
    public Image parseImage (int index) {
        if (images.get(index).startsWith(getTextResources().getString("localfileprefix"))) {
            return new Image("file:" + images.get(index));
        }
        else {
            return new Image(images.get(index));
        }
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaImagesInterface#addImagesListener(javafx.collections.ListChangeListener)
     */
    @Override
    public void addImagesListener (ListChangeListener<String> l) {
        images.addListener(l);
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaPaletteInterface#getColorMap()
     */
    @Override
    public Map<Integer, String> getColorMap () {
        return palette;
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaPaletteInterface#addPaletteListener(javafx.collections.MapChangeListener)
     */
    @Override
    public void addPaletteListener (MapChangeListener<Integer, String> m) {
        palette.addListener(m);
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITADrawerSpeedInterface#setSpeed(double)
     */
    @Override
    public void setSpeed (double speed) {
        drawer.setSpeed(speed);
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITurtleAreaRedrawInterface#getPalette()
     */
    @Override
    public String getPalette () {
        String ans = "";
        for (Integer e : palette.keySet()) {
            ans = ans + e.toString() + "," + palette.get(e) + ",";
        }
        return ans.substring(0, ans.length() - 1);
        // return aa.stream().reduce((t,u) -> t + ',' + palette.get(Integer.parseInt(t)) + ',' + u +
        // ',' + palette.get(Integer.parseInt(u))).get();
    }

    /* (non-Javadoc)
     * @see gui.turtlearea.GUITADrawerSpeedInterface#getSpeed()
     */
    @Override
    public double getSpeed () {
        return drawer.getSpeed();
    }
}
