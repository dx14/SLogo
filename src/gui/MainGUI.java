package gui;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import turtle.Turtle;
import util.Coordinate;
import util.SlogoPath;
import util.StraightPath;


/**
 * This is the class that has the Main GUI
 * 
 */


public class MainGUI implements GUIInterface{
	
	private List<GUIComponent> allGUIComponents;
	private BorderPane mainRoot;
	private Color turtleAreaColor;
	private List<GUITurtle> turtleList;
	private List<SlogoPath> pathList;
	private SLogoLanguage language;
	private GUIController myGUIController;
	private GUITurtleArea myGUITurtleArea;
	private GUIHistory myGUIHistory;
	private GUIPaletteBackground myGUIPaletteBackground;
	
	public MainGUI(BorderPane root, GUIController controller){
	        myGUIController = controller;
	        //turtleList=myGUIController.getGUITurtles();
	        turtleList=new ArrayList<GUITurtle>();
	        pathList=new ArrayList<SlogoPath>();
		turtleAreaColor = Color.WHITE;
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
	        myGUITurtleArea = new GUITurtleArea(turtleAreaColor, turtleList, pathList);
	        allGUIComponents.add(myGUITurtleArea);
	        myGUIHistory=new GUIHistory();
		allGUIComponents.add(myGUIHistory);
		myGUIPaletteBackground = new GUIPaletteBackground(turtleAreaColor, (GUITurtleAreaBGInterface) myGUITurtleArea);
		allGUIComponents.add(myGUIPaletteBackground);
		

		Turtle t = new Turtle();
		t.setXOnGrid(300);
		t.setYOnGrid(100);
		turtleList.add(t);
		t=new Turtle();
		t.setXOnGrid(250);
		t.setYOnGrid(333);
		turtleList.add(t);
		SlogoPath p = new StraightPath(new Coordinate(20,30), new Coordinate(240,120));
		pathList.add(p);
	}
	
	
	public void draw(){
//		
//	
//		for(GUIComponent component : allGUIComponents){
//			 (mainRoot).getChildren().add(component.returnNodeToDraw());
//		}
	    
		mainRoot.setCenter(myGUITurtleArea.returnNodeToDraw());
		mainRoot.setLeft(myGUIHistory.returnNodeToDraw());
		mainRoot.setRight(myGUIPaletteBackground.returnNodeToDraw());
	}

}
