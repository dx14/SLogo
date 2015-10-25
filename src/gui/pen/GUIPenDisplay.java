package gui.pen;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import gui.GUIPaletteTurtle;
import gui.GUITurtle;
import gui.GUITurtleAreaBGInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import parser.structure.GUITurtleContainer;
import parser.structure.TurtleContainer;

public abstract class GUIPenDisplay extends GUIPaletteTurtle{
    private List<String> listOfVariables = new ArrayList<String>();
    private ObservableList<String> whatToShow = FXCollections.observableList(listOfVariables);
    
    public GUIPenDisplay(List<GUITurtle> turtles, GUITurtleAreaBGInterface guiTurtleArea) {
        super(turtles, guiTurtleArea);
    }
    
    @Override
    public Node returnNodeToDraw() {
        ListView<String> whatToGive = new ListView<String>(getWhatToShow());
        whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setVariable();
            }
    });
        if (getTurtles().size()>0) {
        redraw();
        }
        whatToGive.setMaxHeight(30);
        VBox out = new VBox(whatToGive);
        return out;
    }
    
    abstract void redraw();
    abstract void setVariable();
    
    @Override
    public void update (Observable o, Object arg) {
        if(o instanceof TurtleContainer){
            GUITurtle turtle = ((GUITurtleContainer) o).getCurrentTurtle();
            if (getTurtles().size()==0) {
                getTurtles().add(0,null); //TODO: workaround
            }
            getTurtles().set(0, turtle);
            redraw();
            turtle.completeUpdate();
        }

        else{
            //make this an exception
            System.out.println("update didnt update it; it might not be an instance of GUITurtleContainer");
        }
    }

    public ObservableList<String> getWhatToShow () {
        return whatToShow;
    }

    public void setWhatToShow (ObservableList<String> whatToShow) {
        this.whatToShow = whatToShow;
    }
}
