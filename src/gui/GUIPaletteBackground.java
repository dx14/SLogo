package gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class GUIPaletteBackground extends GUIPalette{

	private Color backgroundColor;	
	
	public GUIPaletteBackground(Color col, GUITurtleAreaBGInterface guiTurtleArea){
	    super(guiTurtleArea);
		backgroundColor = col;
	}

	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub
		   final ColorPicker colorPicker = new ColorPicker();
	        colorPicker.setValue(backgroundColor);

	        
	        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	                backgroundColor = (colorPicker.getValue());
	                addToPalette(backgroundColor);
	                getMyGuiTurtleArea().updateBackgroundColor(backgroundColor);
	                //let GUITurtleArea know
	                //System.out.println(backgroundColor);
	            }
	        });
		
		return colorPicker;
	}
	
	public void changeBackgroundColor(){
	
		
		
	}
	
	
}
