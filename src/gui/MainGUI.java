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

public class MainGUI implements GUIInterface {

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
	private GUIUserDefinedCommands myUserDefinedCommands;

	public MainGUI(BorderPane root, Stage stage, GUIController controller) {
		myGUIController = controller;
		// turtleList=myGUIController.getGUITurtles();
		turtleList = new ArrayList<GUITurtle>();
		pathList = new ArrayList<SlogoPath>();
		turtleAreaColor = Color.WHITE;
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
		mainStage = stage;

		myGUIConsole = new GUIConsole(myGUIController);

		myGUITurtleArea = new GUITurtleArea(mainStage, turtleAreaColor, turtleList, pathList);
		allGUIComponents.add(myGUITurtleArea);
		myGUIHistory = new GUIHistory((GUIConsoleTextEditable) myGUIConsole);
		allGUIComponents.add(myGUIHistory);
		myGUIPaletteBackground = new GUIPaletteBackground(turtleAreaColor, (GUITurtleAreaBGInterface) myGUITurtleArea);
		myGUIPaletteTurtle = new GUIPaletteTurtle(turtleList, (GUITurtleAreaBGInterface) myGUITurtleArea);
		allGUIComponents.add(myGUIPaletteBackground);
		myGUIToolbar = new GUIToolbar(mainStage, turtleList, (GUITurtleAreaRedrawInterface) myGUITurtleArea,
				myGUIController);
		myGUIVariables = new GUIVariableList(myGUIController);
		// allGUIComponents.add(myGUIVariables);
		myUserDefinedCommands = new GUIUserDefinedCommands((GUIConsoleTextEditable) myGUIConsole);
		// temporary test seeds
//		Turtle t = new Turtle();
//		t.setXOnGrid(0);
//		t.setYOnGrid(0);
//		turtleList.add(t);
//		t = new Turtle();
//		t.setXOnGrid(-100);
//		t.setYOnGrid(150);
//		t.setAngle(160);
//		turtleList.add(t);
//		SlogoPath p = new StraightPath(new Coordinate(150, -100), new Coordinate(0, 0));
//		pathList.add(p);
	}

	public void draw() {
		mainRoot.setCenter(myGUITurtleArea.returnNodeToDraw());
		mainRoot.setLeft(new VBox(new Label("History"), myGUIHistory.returnNodeToDraw(), 
				new Label("Variables"),	myGUIVariables.returnNodeToDraw(), 
				new Label("User Defined Commands"), myUserDefinedCommands.returnNodeToDraw()));

		mainRoot.setRight(new VBox(new Label("Background Color:"), // resource
																	// file
				myGUIPaletteBackground.returnNodeToDraw(), new Label("Pen Color:"), // resource
																					// file
				myGUIPaletteTurtle.returnNodeToDraw()));
		mainRoot.setTop(myGUIToolbar.returnNodeToDraw());
		mainRoot.setBottom(myGUIConsole.returnNodeToDraw());
		// mainRoot.setLeft(myGUIVariables.returnNodeToDraw());
	}

	public void updateTurtleArea() {
		myGUITurtleArea.drawAll();
	}

	public Observer showObserverVariables() {
		return (Observer) myGUIVariables;
	}
	
	       public Observer showTurtleArea() {
	                return (Observer) myGUITurtleArea;
	        }

	public Observer showUserDefinedCommands() {
		return (Observer) myUserDefinedCommands;
	}

	public UpdatableHistory showHistory() {
		return (UpdatableHistory) myGUIHistory;
	}

}
