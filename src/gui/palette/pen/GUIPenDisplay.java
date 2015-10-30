package gui.palette.pen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import gui.modelinterface.GUITurtle;
import gui.modelinterface.GUITurtleContainer;
import gui.palette.GUIPaletteTurtle;
import gui.turtlearea.GUITurtleAreaBGInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import parser.structure.TurtleContainer;


/**
 * The abstract Class GUIPenDisplay extends GUIPaletteTurtle in order to communicate with the back end turtles.
 * It lays the ground work of communicating with the pens of the backend turtles.
 * @author John
 */
public abstract class GUIPenDisplay extends GUIPaletteTurtle {
    private List<String> listOfVariables = new ArrayList<String>();
    private ObservableList<String> whatToShow = FXCollections.observableList(listOfVariables);
    public static final double HEIGHT = 30;
    
    /**
     * Instantiates a new GUI pen display.
     *
     * @param turtles the turtles
     * @param guiTurtleArea the gui turtle area
     */
    protected GUIPenDisplay (List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
    }

    /* (non-Javadoc)
     * @see gui.palette.GUIPaletteTurtle#returnNodeToDraw()
     */
    @Override
    public Node returnNodeToDraw () {
        ListView<String> whatToGive = new ListView<String>(getWhatToShow());
        whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                setVariable();
            }
        });
        if (getTurtles().size() > 0) {
            redraw();
        }
        whatToGive.setMaxHeight(HEIGHT);
        VBox out = new VBox(whatToGive);
        return out;
    }

    /**
     * Redraw.
     */
    protected abstract void redraw ();

    /**
     * Sets the target variable.
     */
    protected abstract void setVariable ();

    /* (non-Javadoc)
     * @see gui.palette.GUIPaletteTurtle#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (o instanceof TurtleContainer) {
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            getTurtles().set(0, turtle);
            redraw();
            turtle.completeUpdate();
        }
    }

    /**
     * Gets the data to show.
     *
     * @return the what to show
     */
    public ObservableList<String> getWhatToShow () {
        return whatToShow;
    }

    /**
     * Sets the data to show.
     *
     * @param whatToShow the new what to show
     */
    public void setWhatToShow (ObservableList<String> whatToShow) {
        this.whatToShow = whatToShow;
    }
}
