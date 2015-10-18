package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collector;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import parser.structure.VariableContainer;
import util.GUIVariable;

public class GUIVariableList extends GUIComponent implements Observer {

	private GUIController guiController;

	private List<String> listOfVariables;

	private ObservableList<String> whatToShow;
	private List<GUIVariable> oo;

	public GUIVariableList(GUIController gui) {

		guiController = gui;
		listOfVariables = new ArrayList<String>();
		whatToShow = FXCollections.observableList(listOfVariables);

	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub

		ListView<String> whatToGive = new ListView<String>(whatToShow);
		whatToGive.setPlaceholder(new Label("No variables In List")); // move to
																		// resources
		whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				TextInputDialog aaa = new TextInputDialog();

				TextInputDialog dialog = new TextInputDialog("0");
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("Look, a Text Input Dialog");
				dialog.setContentText("Please enter your name:");
				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {
					try{
					oo.stream()
							.filter(variable -> variable.toString()
									.equals(whatToGive.getSelectionModel().getSelectedItem().toString()))
							.forEach(variable -> variable.setValue(Double.parseDouble(result.get())));
					
					whatToShow.clear();
					oo.stream().forEach(e -> whatToShow.add(e.toString()));
					}
					catch (Exception e){
			            handleException(e);

					}
					System.out.println("Your name: " + result.get());
				}

			}
		});

		return whatToGive;
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof VariableContainer) {
			oo = ((VariableContainer) o).getVariables();
			/*oo.stream().forEach(e -> {
				if (!whatToShow.contains(e.toString())) {
					whatToShow.add(e.toString());
				}
			});
*/
			whatToShow.clear();
			oo.stream().forEach(e -> whatToShow.add(e.toString()));	
		}

		else {
			// make this an exception
			System.out.println("update didnt update it; it might not be an instance of GUIVariableContainer");
		}

	}

}
