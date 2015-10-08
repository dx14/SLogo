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
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
		allGUIComponents.add(new GUIHistory());
		//allGUIComponents.add(new GUIPaletteBackground(turtleAreaColor));
		
		//TextArea a = new TextArea();
		
		
	}
	
	
	public void draw(){
	
		for(GUIComponent component : allGUIComponents){
			 (mainRoot).getChildren().add(component.returnNodeToDraw());
		}
	}

}
