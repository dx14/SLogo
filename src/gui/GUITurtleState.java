package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

public class GUITurtleState extends GUIComponent implements Observer{
    private List<GUITurtle> myTurtles;
    private List<String> listOfVariables = new ArrayList<String>();
    
    public GUITurtleState(List<GUITurtle> turtles) {
        myTurtles=turtles;
    }
    
    @Override
    public void update (Observable o, Object arg) {
        ListView<String> whatToGive = new ListView<String>(whatToShow);
        whatToGive.setPlaceholder(new Label("No variables In List")); // move to

        return whatToGive;
    }

    @Override
    public Node returnNodeToDraw () {
        // TODO Auto-generated method stub
        return null;
    }

}
