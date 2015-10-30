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
    private final String TURTLE_AREA_FILE = "resources.guitext.TurtleArea";
    private GUITurtleAreaDrawer drawer;

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

    @Override
    public Node returnNodeToDraw () {
        drawAll();
        return drawer.returnNodeToDraw();
    }

    @Override
    public void updateBackgroundColor (Color c) {
        backgroundColor = c;
        drawer.drawBackground(backgroundColor);
    }

    @Override
    public void drawAll () {
        drawTurtles();
        drawPaths();
    }

    private void drawTurtles () {
        myTurtles.stream().forEach(t -> drawTurtle(t));
    }

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

    private void checkPen (SlogoPath path) {
        if (path.getPen().isDown()) {
            myPaths.add(path);
        }
    }

    private void drawPaths () {
        for (SlogoPath path : myPaths) {
            drawer.drawPath(path, Color.valueOf(palette.get(path.getPen().getColor())));
        }
    }

    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            turtleObserved(turtle);
            turtle.completeUpdate();
        }
    }

    @Override
    public void addImage (String image) {
        images.add(image);
    }

    @Override
    public int getImagesSize () {
        return images.size();
    }

    @Override
    public String getBGColor () {
        return backgroundColor.toString();
    }

    @Override
    public String getImagePathsAsAString () {
        return images.stream().reduce( (t, u) -> t + "," + u).get();
    }

    @Override
    public Image parseImage (int index) {
        if (images.get(index).startsWith(getTextResources().getString("localfileprefix"))) {
            return new Image("file:" + images.get(index));
        }
        else {
            return new Image(images.get(index));
        }
    }

    @Override
    public void addImagesListener (ListChangeListener<String> l) {
        images.addListener(l);
    }

    @Override
    public Map<Integer, String> getColorMap () {
        return palette;
    }

    @Override
    public void addPaletteListener (MapChangeListener<Integer, String> m) {
        palette.addListener(m);
    }

    @Override
    public void setSpeed (double speed) {
        drawer.setSpeed(speed);
    }

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

    @Override
    public double getSpeed () {
        return drawer.getSpeed();
    }
}
