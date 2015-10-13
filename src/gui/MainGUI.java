package gui;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * This is the class that has the Main GUI
 * 
 */


public class MainGUI implements GUIInterface{
	
	private List<GUIComponent> allGUIComponents;
	private Pane mainRoot;
	private Color turtleAreaColor;
	private List<GUITurtle> turtleList;
	private SLogoLanguage language;
	
	
	
	public MainGUI(Pane root){

		turtleAreaColor = Color.WHITE;

		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
		allGUIComponents.add(new GUIHistory());
		
		GUITurtleArea guiTurtleArea = new GUITurtleArea(turtleAreaColor);
		
		allGUIComponents.add(new GUIPaletteBackground(turtleAreaColor, (GUITurtleAreaBGInterface) guiTurtleArea));
		
		allGUIComponents.add(guiTurtleArea);
		
		
	}
	
	
	public void draw(){
		
	
		for(GUIComponent component : allGUIComponents){
			 (mainRoot).getChildren().add(component.returnNodeToDraw());
		}
	}

}
