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

	
	public GUIHistory(){
		
		textHistory = new ArrayList<String>();
	}	
	@Override
	public Node returnNodeToDraw() {
	
	
		myList = FXCollections.observableList(textHistory);
		ListView whatToShow = new ListView(myList);
		//this will be guiController.getObservableHistory();
    
		whatToShow.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {
	            System.out.println("clicked on " + whatToShow.getSelectionModel().getSelectedItem());
	        }
	    });
		
		
		return whatToShow; 
	}
	
	public void addToHistory(String arg){
		myList.add(arg);
	}
	
	

}
