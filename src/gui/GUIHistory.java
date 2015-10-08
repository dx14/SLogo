package gui;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GUIHistory extends GUIComponent{

	
	private ScrollPane historyBox;
	private List<String> textHistory;
	private List<String> formattedTextHistory;
	
	
	GUIController guiController;

	
	@Override
	public Node returnNodeToDraw() {
		
		StackPane historyBox = new StackPane();
		
		//fill the history box here;
		historyBox.setTranslateX(100);
		historyBox.setTranslateY(100);
		TextArea a = new TextArea();
		//a.setPrefSize(1, 1);
		
		historyBox.getChildren().add(a);
		
		historyBox.setPrefSize(100, 100);
		
		
		
		
		
		
		return historyBox; 
	}

}
