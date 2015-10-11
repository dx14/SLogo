package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
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
	
	GUIController guiController;

	
	public GUIHistory(){
		
		textHistory = new ArrayList<String>();
		textHistory.add("fw 50");
		textHistory.add("fw 200");
		textHistory.add("fw 200");
		}
	
	@Override
	public Node returnNodeToDraw() {
	
		//historyBox.getChildren().add(link);
		hpls = new Hyperlink[textHistory.size()];
		
		
		 for (int i = 0; i < textHistory.size(); i++) {
            final Hyperlink hpl = new Hyperlink(textHistory.get(i));
            hpls[i] = hpl;
            final int j = i;
            
            hpl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                    	
                    	//right now guiController is null;
                    	
						guiController.runCommand(hpls[j].getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
                    System.out.println("Hey, you did something amazing by clicking on " + hpls[j].getText() );
                }
            });
        }
		
		 
		  VBox vbox = new VBox();
		  
		  vbox.setPrefSize(100, 100);
		  
		
		  
		  
	        vbox.getChildren().addAll(hpls);
	        vbox.setSpacing(5);
	        
	        ScrollPane historyBox = new ScrollPane();
	        historyBox.setTranslateX(-100);
	        historyBox.setTranslateY(-100);
	        historyBox.setMaxSize(100, 100);
	        
	        
	        historyBox.setContent(vbox);
	        
		
		return historyBox; 
	}

}
