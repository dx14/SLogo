package gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class GUIPaletteBackground extends GUIPalette{

	private Color backgroundColor;
	private GUITurtleAreaBGInterface myGuiTurtleArea;
	
	
	public GUIPaletteBackground(Color col, GUITurtleAreaBGInterface guiTurtleArea){
		backgroundColor = col;
		myGuiTurtleArea = guiTurtleArea;
		
	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub
		   final ColorPicker colorPicker = new ColorPicker();
	        colorPicker.setValue(Color.WHITE);

	        
	        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	                backgroundColor = (colorPicker.getValue());
	                myGuiTurtleArea.updateBackgroundColor(backgroundColor);
	                //let GUITurtleArea know
	                //System.out.println(backgroundColor);
	            }
	        });
		
		return colorPicker;
	}
	
	public void changeBackgroundColor(){
	
		
		
	}
	
	
}
