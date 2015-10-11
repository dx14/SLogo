package gui;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GUITurtleArea extends GUIComponent{

	private List<GUITurtle> myTurtles;
	private Color backgroundColor;
	
	
	
	
	@Override
	public Node returnNodeToDraw() {
		// TODO Auto-generated method stub
		
		Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
      
        
        gc.setFill(backgroundColor);
        gc.fillRect(50, 50, 50, 50);
		
		
		return canvas;
	}
	
	

}
