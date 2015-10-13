package gui;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;


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
	private GUIController myGUIController;
	
	
	
	public MainGUI(Pane root, GUIController controller){
	        myGUIController = controller;
	        //turtleList=myGUIController.getGUITurtles();
	        turtleList=new ArrayList<GUITurtle>();
		turtleAreaColor = Color.WHITE;
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
	        GUITurtleArea guiTurtleArea = new GUITurtleArea(turtleAreaColor, turtleList);
	        allGUIComponents.add(guiTurtleArea);
		allGUIComponents.add(new GUIHistory());
		allGUIComponents.add(new GUIPaletteBackground(turtleAreaColor, (GUITurtleAreaBGInterface) guiTurtleArea));
		

		Turtle t = new Turtle();
		t.setXOnGrid(300);
		t.setYOnGrid(100);
		turtleList.add(t);
		t=new Turtle();
		t.setXOnGrid(250);
		t.setYOnGrid(333);
		turtleList.add(t);
	}
	
	
	public void draw(){
		
	
		for(GUIComponent component : allGUIComponents){
			 (mainRoot).getChildren().add(component.returnNodeToDraw());
		}
	}

}
