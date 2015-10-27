package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import gui.pen.GUIPenDisplay;
import gui.pen.GUIPenDisplayContainer;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
	private GUIPenDisplayContainer myGUIPen;
	private GUIToolbar myGUIToolbar;
	private GUIConsole myGUIConsole;
	private GUIVariableList myGUIVariables;
	private GUIUserDefinedCommands myUserDefinedCommands;
	private GUIImageDisplay myGUIImageDisplay;
	private GUIPaletteDisplay myGUIPaletteDisplay;

	private GUIParameter myParams;

	public MainGUI(BorderPane root, Stage stage, GUIController controller) {

		// debating over using src/resources
		try {
			XMLParser a = new XMLParser(new File("src/resources/default.xml"));
			myParams = a.getParameters();
			
			
			/*
			String output = myParams.getImageList().stream().reduce((t, u) -> t + "," + u).
            get();
			
			a.saveToXML("src/resources/default.xml", myParams.getDefaultBackground().toString(),
					output, myParams.getCommandLanguage()); */

		} catch (GUIException e) {
			e.printStackTrace();
		}

		myGUIController = controller;
		// turtleList=myGUIController.getGUITurtles();
		turtleList = new ArrayList<GUITurtle>();
		pathList = new ArrayList<SlogoPath>();
		turtleAreaColor = Color.WHITE;
		allGUIComponents = new ArrayList<GUIComponent>();
		mainRoot = root;
		mainStage = stage;

		myGUIConsole = new GUIConsole(myGUIController);
		myGUITurtleArea = new GUITurtleArea(mainStage, myParams.getDefaultBackground(), turtleList, pathList, myParams.getImageList());
		allGUIComponents.add(myGUITurtleArea);
		myGUIHistory = new GUIHistory((GUIConsoleTextEditable) myGUIConsole);
		allGUIComponents.add(myGUIHistory);
		myGUIPaletteBackground = new GUIPaletteBackground(myParams.getDefaultBackground(),
				(GUITurtleAreaPaletteInterface) myGUITurtleArea);
		myGUIPen = new GUIPenDisplayContainer(turtleList, (GUITurtleAreaBGInterface) myGUITurtleArea);
		allGUIComponents.add(myGUIPaletteBackground);
		myGUIToolbar = new GUIToolbar(mainStage, turtleList, (GUITurtleAreaRedrawInterface) myGUITurtleArea,
				myGUIController, myParams.getCommandLanguage());
		myGUIVariables = new GUIVariableList(myGUIController);
		// allGUIComponents.add(myGUIVariables);
		myUserDefinedCommands = new GUIUserDefinedCommands((GUIConsoleTextEditable) myGUIConsole);
		myGUIImageDisplay = new GUIImageDisplay((GUITurtleAreaImagesInterface)myGUITurtleArea);
		myGUIPaletteDisplay = new GUIPaletteDisplay((GUITurtleAreaPaletteInterface)myGUITurtleArea);
	}

	public void draw() {
		mainRoot.setCenter(myGUITurtleArea.returnNodeToDraw());
		mainRoot.setLeft(new VBox(new Label("History"), myGUIHistory.returnNodeToDraw(), new Label("Variables"),
				myGUIVariables.returnNodeToDraw(), new Label("User Defined Commands"),
				myUserDefinedCommands.returnNodeToDraw()));

		mainRoot.setRight(new VBox(new Label("Background Color:"), // resource
																	// file
				myGUIPaletteBackground.returnNodeToDraw(), new Label("Pen Color:"), // resource
																					// file
				myGUIPen.returnNodeToDraw(),
				myGUIImageDisplay.returnNodeToDraw(),
				myGUIPaletteDisplay.returnNodeToDraw()));
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

	public Observer showTurtlePen() {
		return (Observer) myGUIPen;
	}

	@Override
	public Object updateGUINumber() {
		myGUIToolbar.updateGUINumber();
		// TODO Auto-generated method stub
		return null;
	}

}
