package gui;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUITurtleArea extends GUIComponent implements GUITurtleAreaBGInterface{

	private List<GUITurtle> myTurtles;
	private Color backgroundColor;
	
	private Canvas canvas; 
	
	
	public GUITurtleArea(Color turtleAreaColor) {

	backgroundColor = turtleAreaColor;
	
	canvas = new Canvas(100, 100);

	}




	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub
		
        GraphicsContext gc = canvas.getGraphicsContext2D();
      
        
        gc.setFill(backgroundColor);
        gc.fillRect(50, 50, 50, 50);
		
		
        
        
		return canvas;
	}




	@Override
	public void updateBackgroundColor(Color c) {
		// TODO Auto-generated method stub
		  GraphicsContext gc = canvas.getGraphicsContext2D();
		
		  backgroundColor = c;
		  
		  
		 gc.setFill(backgroundColor);
	     gc.fillRect(50, 50, 50, 50);
		
		
	}
	
	

}
