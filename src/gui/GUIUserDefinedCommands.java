package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import parser.structure.CommandContainer;
import parser.structure.GUICommand;
import parser.structure.GUICommandContainer;
import parser.structure.VariableContainer;
import util.GUIVariable;

public class GUIUserDefinedCommands extends GUIComponent implements Observer{


	private List<String> listOfVariables;

	private ObservableList<String> whatToShow;
	private GUIConsoleTextEditable guiConsole;

	
	
	public GUIUserDefinedCommands(GUIConsoleTextEditable myGUIConsole) {

		listOfVariables = new ArrayList<String>();
		whatToShow = FXCollections.observableList(listOfVariables);
		guiConsole = myGUIConsole;


	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub


		ListView<String> whatToGive = new ListView<String>(whatToShow);
		whatToGive.setPlaceholder(new Label("No commands defined yet")); //move to resources

		whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            guiConsole.changeText(whatToGive.getSelectionModel().getSelectedItem().toString().split("\\n+")[0]);
            
        }
    });
		

		return whatToGive;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof GUICommandContainer){
			List<GUICommand> oo = ((GUICommandContainer) o).getCommands();
			whatToShow.clear();
		oo.stream().forEach(e -> { whatToShow.add(e.getInputString() + "\n" + e.getCommandText());   });
		
		}
		
		else{
			//make this an exception
			System.out.println("update didnt update it; it might not be an instance of GUIVariableContainer");
		}
		
	}
	
}
