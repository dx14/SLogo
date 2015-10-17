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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import parser.ParserException;

public class GUIHistory extends GUIComponent{

	
	//private StackPane historyBox;
	private List<String> textHistory;
	private List<String> formattedTextHistory;
	private Hyperlink[] hpls;
	
	private ObservableList<String> myList;
	
	private GUIController guiController;

	
	public GUIHistory(){
		
		textHistory = new ArrayList<String>();
		textHistory.add("fw 50");
		textHistory.add("fw 200");
		textHistory.add("fw 200");
		myList =  FXCollections.observableArrayList(
		          "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
		}
	
	@Override
	public Node returnNodeToDraw() {
	
	
		
		ListView whatToShow = new ListView(myList);
		//this will be guiController.getObservableHistory();
    
		
		//historyBox.getChildren().add(link);
		hpls = new Hyperlink[textHistory.size()];
		
		
		 for (int i = 0; i < textHistory.size(); i++) {
            final Hyperlink hpl = new Hyperlink(textHistory.get(i));
            hpls[i] = hpl;
            final int j = i;
            
		 }
		
		 
		  VBox vbox = new VBox();
		  
		  vbox.setPrefSize(100, 100);
		  
		
		  
		  
	        vbox.getChildren().addAll(hpls);
	        vbox.setSpacing(5);
	        
	        ScrollPane historyBox = new ScrollPane();
	        //historyBox.setTranslateX(-100);
	        //historyBox.setTranslateY(-100);
	        historyBox.setMaxSize(100, 200);
	        
	        
	        historyBox.setContent(vbox);
	        
		
		return whatToShow; 
	}

}
