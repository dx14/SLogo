package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import parser.structure.VariableContainer;
import util.GUIVariable;

public class GUIVariableList extends GUIComponent implements Observer {

	GUIController guiController;

	List<String> listOfVariables;

	ObservableList<String> whatToShow;

	public GUIVariableList() {

		listOfVariables = new ArrayList<String>();
		whatToShow = FXCollections.observableList(listOfVariables);

	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub


		ListView<String> whatToGive = new ListView<String>(whatToShow);
		whatToGive.setPlaceholder(new Label("No variables In List")); //move to resources
		whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	        	
	        	 ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
	        	 Dialog<ButtonType> dialog = new Dialog<>();
	        	 dialog.getDialogPane().getButtonTypes().add(loginButtonType);
	        	 boolean disabled = false; // computed based on content of text fields, for example
	        	 dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
	        
	        	 Optional<ButtonType> result = dialog.showAndWait();
	        	 if (result.isPresent() && result.get() == ButtonType.OK) {
	        		 System.out.println("dialog");
	        	 }
	        	 else{
	        		 System.out.println("whats going on");
	        	 }
	        	 
	        	 
	        	
	        }
	    });

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
