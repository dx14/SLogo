package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import parser.ParserException;

public class GUIHistory extends GUIComponent implements UpdatableHistory{

	
	//private StackPane historyBox;
	private List<String> textHistory;
	private List<String> formattedTextHistory;
	private Hyperlink[] hpls;
	
	private ObservableList<String> myList;
	
	private GUIController guiController;
	private GUIConsoleTextEditable guiConsole;
	
	
	public GUIHistory(GUIConsoleTextEditable myGUIConsole) {
		// TODO Auto-generated constructor stub
		textHistory = new ArrayList<String>();
		myList = FXCollections.observableList(textHistory);
		guiConsole = myGUIConsole;
		
		
	}
	@Override
	public Node returnNodeToDraw() {
	
	
		ListView<String> whatToGive = new ListView<String>(myList);
		whatToGive.setPlaceholder(new Label("No commands entered")); //move to resources
		

		//this will be guiController.getObservableHistory();
    
		whatToGive.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	            guiConsole.changeText(whatToGive.getSelectionModel().getSelectedItem().toString());
	            
	        }
	    });
		
		
		return whatToGive; 
	}
	
	public void addToHistory(String arg){
		myList.add(arg);
	}
	
	

}
