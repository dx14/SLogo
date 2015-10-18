package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import parser.structure.VariableContainer;
import util.GUIVariable;

public class GUIVariableList extends GUIComponent implements Observer {

	GUIController guiController;

	List<String> listOfVariables;

	ObservableList<String> whatToShow;

	public GUIVariableList() {

		listOfVariables = new ArrayList<String>();

	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub

		whatToShow = FXCollections.observableList(listOfVariables);

		ListView<String> whatToGive = new ListView<String>(whatToShow);
		whatToGive.setPlaceholder(new Label("No variables In List")); //move to resources


		return whatToGive;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof VariableContainer){
			List<GUIVariable> oo = ((VariableContainer) o).getVariables();
		oo.stream().forEach(e -> {if(!whatToShow.contains(e.toString())){whatToShow.add(e.toString());}   });
		
		}
		
		else{
			//make this an exception
			System.out.println("update didnt update it; it might not be an instance of GUIVariableContainer");
		}
		
	}

}
