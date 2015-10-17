package gui;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
	private Stage mainStage;
	private Color turtleAreaColor;
	private List<GUITurtle> turtleList;
	private List<SlogoPath> pathList;
	private SLogoLanguage language;
	private GUIController myGUIController;
	private GUITurtleArea myGUITurtleArea;
	private GUIHistory myGUIHistory;
	private GUIPaletteBackground myGUIPaletteBackground;
	private GUIPaletteTurtle myGUIPaletteTurtle;
	private GUIToolbar myGUIToolbar;
	private GUIConsole myGUIConsole;
	private GUIVariableList myGUIVariables;
	
	public MainGUI(BorderPane root, Stage stage, GUIController controller){
	        myGUIController = controller;
	        //turtleList=myGUIController.getGUITurtles();
	        turtleList=new ArrayList<GUITurtle>();
	        pathList=new ArrayList<SlogoPath>();
		turtleAreaColor = Color.WHITE;
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
		mainStage = stage;
	        myGUITurtleArea = new GUITurtleArea(mainStage, turtleAreaColor, turtleList, pathList);
	        allGUIComponents.add(myGUITurtleArea);
	        myGUIHistory=new GUIHistory();
		allGUIComponents.add(myGUIHistory);
		myGUIPaletteBackground = new GUIPaletteBackground(turtleAreaColor, (GUITurtleAreaBGInterface) myGUITurtleArea);
		myGUIPaletteTurtle = new GUIPaletteTurtle(turtleList, (GUITurtleAreaBGInterface)myGUITurtleArea);
		allGUIComponents.add(myGUIPaletteBackground);
		myGUIToolbar = new GUIToolbar(mainStage, turtleList, (GUITurtleAreaRedrawInterface) myGUITurtleArea, myGUIController);
		myGUIConsole = new GUIConsole(myGUIController);
		
		//temporary test seeds
		Turtle t = new Turtle();
		t.setXOnGrid(0);
		t.setYOnGrid(0);
		turtleList.add(t);
		t=new Turtle();
		t.setXOnGrid(-100);
		t.setYOnGrid(150);
		t.setAngle(160);
		turtleList.add(t);
		SlogoPath p = new StraightPath(new Coordinate(150,-100), new Coordinate(0,0));
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
		mainRoot.setRight(new VBox(new Label("Background Color:"),
		                           myGUIPaletteBackground.returnNodeToDraw(),
		                           new Label("Pen Color:"),
		                           myGUIPaletteTurtle.returnNodeToDraw()));
		mainRoot.setTop(myGUIToolbar.returnNodeToDraw());
		mainRoot.setBottom(myGUIConsole.returnNodeToDraw());
	}
	public void updateTurtleArea() {
	    myGUITurtleArea.drawAll();
	}
	
	public Observer showObserverVariables(){
		return (Observer) myGUIVariables;
	}
}
