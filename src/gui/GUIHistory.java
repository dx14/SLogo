package gui;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GUIHistory extends GUIComponent{

	
	private ScrollPane historyBox;
	private List<String> textHistory;
	private List<String> formattedTextHistory;
	
	
	GUIController guiController;

	
	@Override
	public Node returnNodeToDraw() {
		
		historyBox = new ScrollPane();
		
		//fill the history box here;
		
		
		
		return historyBox;
	}

}
