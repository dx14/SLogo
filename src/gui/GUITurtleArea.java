package gui;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import parser.structure.GUITurtleContainer;
import parser.structure.TurtleContainer;
import util.SlogoPath;


public class GUITurtleArea extends GUIComponent
        implements GUITurtleAreaImagesInterface, GUITurtleAreaPaletteInterface, Observer {

    private static final double DASH_LENGTH = 10;
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
                          List<String> imagePaths) {
        setTextResources(ResourceBundle.getBundle(TURTLE_AREA_FILE));
        xCanvas =
                mainStage.getScene().getWidth() -
                  Integer.parseInt(getTextResources().getString("canvasxshrinkpixels"));
        yCanvas =
                mainStage.getScene().getHeight() -
                  Integer.parseInt(getTextResources().getString("canvasyshrinkpixels"));
        drawer = new GUITurtleAreaDrawer(xCanvas, yCanvas);

        backgroundColor = turtleAreaColor;
        myTurtles = turtles;
        myPaths = paths;

        images = FXCollections.observableArrayList(imagePaths);
        palette = FXCollections.observableHashMap();
        
        drawer.drawBackground(backgroundColor);

        
        palette.put(0, "0xff00ffff"); // TODO:default
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
        for (GUITurtle turtle : myTurtles) {
            drawTurtle(turtle);
        }
    }

    private void drawTurtle (GUITurtle turtle) {
    	Image image;
        try {
            image = parseImage(turtle.getDisplayIndex());
      //      drawer.drawTurtleImage(turtle, image);
            drawer.drawTurtleImageView(turtle, image);
        }
        catch (Exception e) {
            handleException(e);
            image =
                    new Image((getTextResources().getString("defaultimageurl")));
        //    drawer.drawTurtleImage(turtle, image);
            drawer.drawTurtleImageView(turtle, image);

        }

        
    }

    private void turtleObserved (GUITurtle turtle) {
        myTurtles.clear();
        if (turtle.isShowing()) {
            System.out.println("TURTLE IS SHOWING");
            myTurtles.add(turtle);
        }
        myPaths.clear();
        if (!turtle.isClear()) {
            for (SlogoPath path : turtle.getHistory()) {
                checkPen(path);
            }
            for (SlogoPath path : turtle.getPaths()) {
                checkPen(path);
            }
        }
        drawAll();
        // myPaths.stream().forEach(s -> System.out.println(s.toString()));
        // if (turtle.getPaths().size()>0){
        // for(SlogoPath p : turtle.getPaths()){
        // drawPath(p);
        // }
        // }
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
        if (images.get(index).startsWith("C:")) {
            return new Image("file:" + images.get(index));
        }
        else {
            return new Image(images.get(index));
        }
    }

    @Override
    public void addImagesListener (ListChangeListener l) {
        images.addListener(l);
    }

    @Override
    public Map<Integer, String> getColorMap () {
        return palette;
    }

    @Override
    public void addPaletteListener (MapChangeListener l) {
        palette.addListener(l);
    }

}
